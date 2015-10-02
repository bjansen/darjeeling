darjeelingApp.controller('FeedsCtrl', ['$scope', 'customHttp', '$mdSidenav', '$mdDialog', '$interval', 'Mousetrap',
    function ($scope, customHttp, $mdSidenav, $mdDialog, $interval, Mousetrap) {
        var $http = customHttp.getHttp();
        var selectedItemIndex = -1;
        var interval;
        var serverDown = false;

        var onError = function(response) {
        	$scope.loading = false;
        	
        	if (response.status == 401) {
        		window.location.reload();
        	} else if (response.status == 0) {
        		window.document.title = "(×_×) Darjeeling";
        		serverDown = true;
        		
        	    $mdDialog.show(
	    	      $mdDialog.alert()
	    	        .parent(document.body)
	    	        .clickOutsideToClose(false)
	    	        .title('Awwww')
	    	        .content('The server cannot be reached :-(')
	    	        .ariaLabel('Server unreachable')
	    	        .ok('Try again!')
	    	    ).then(function() {
	    	    	serverDown = false;
	    	    	updateUnreadCount();
	    	    });
        	}
        };
        
        var updateFeeds = function () {
            $http.get('/rest/feeds/all').then(function (response) {
            	var data = response.data
                $scope.folders = data.filter(function (el) {
                    return el['feeds'] !== undefined;
                });
                $scope.feeds = data.filter(function (el) {
                    return el['feeds'] === undefined;
                });

                if (interval === undefined) {
                    interval = $interval(updateUnreadCount, 60 * 1000);
                }
                updateUnreadCount();
                $scope.displayUnreadItems($scope.selectedFeed);
            }, onError);
        };

        $scope.$on('feedAdded', updateFeeds);

        var updateUnreadCount = function () {
        	if (serverDown) {
        		return;
        	}
        	
            $scope.loading = true;
            $http.get('/rest/feeds/countUnread').then(function (response) {
            	var data = response.data
                var totalUnread = 0;
                var folderUnread;

                for (var i = 0; i < $scope.folders.length; i++) {
                    folderUnread = 0;

                    for (var j = 0; j < $scope.folders[i].feeds.length; j++) {
                        var feed = $scope.folders[i].feeds[j];
                        if (data[feed.id] != undefined) {
                            feed.unread = data[feed.id];
                            folderUnread += data[feed.id];
                        } else {
                            feed.unread = 0;
                        }
                    }

                    $scope.folders[i].unread = folderUnread;
                    totalUnread += folderUnread;
                }

                for (j = 0; j < $scope.feeds.length; j++) {
                    feed = $scope.feeds[j];
                    if (data[feed.id] != undefined) {
                        feed.unread = data[feed.id];
                        totalUnread += data[feed.id];
                    } else {
                        feed.unread = 0;
                    }
                }

                $scope.totalUnread = totalUnread;
                updateTitle();
                $scope.loading = false;
            }, onError);
        };

        var updateTitle = function () {
            if ($scope.totalUnread > 0) {
                window.document.title = "(" + $scope.totalUnread + ") Darjeeling";
            } else {
                window.document.title = "Darjeeling";
            }
        };

        updateFeeds();

        $scope.items = [];
        $scope.prefs = {
            showUnreadOnly: true
        };

        $scope.displayUnreadItems = function (feedOrFolder, selectFirst) {
            if ($scope.folders === undefined) {
                return; // we're not finished loading feeds
            }
            var url;
            var params = {
                unreadOnly: $scope.prefs.showUnreadOnly
            };

            if (feedOrFolder === '-1') {
                url = '/rest/feeds/items';
            } else if (feedOrFolder.feeds !== undefined) {
                url = '/rest/feeds/itemsInFolder';
                params.folderId = feedOrFolder.id;
            } else {
                url = '/rest/feeds/items';
                params.feedId = feedOrFolder.id;
            }

            $scope.loading = true;
            $http.get(url, {params: params}).then(
                function (response) {
                    $scope.items = response.data;

                    if (selectFirst && $scope.items.length > 0) {
                        $scope.selectItem($scope.items[0]);
                    }
                    $scope.loading = false;
                },
                onError
            );
        };

        $scope.toggle = function (folder) {
            if (folder.expanded === undefined) {
                folder.expanded = false;
            }
            folder.expanded = !folder.expanded;
        };

        $scope.toggleSidenav = function (menuId) {
            $mdSidenav(menuId).toggle();
        };

        $scope.getCurrentFeed = function (item) {

            for (var i = 0; i < $scope.folders.length; i++) {
                for (var j = 0; j < $scope.folders[i].feeds.length; j++) {
                    var feed = $scope.folders[i].feeds[j];
                    if (feed.id === item.feedId) {
                        return feed;
                    }
                }
            }

            for (i = 0; i < $scope.feeds.length; i++) {
                if ($scope.feeds[i].id === item.feedId) {
                    return $scope.feeds[i];
                }
            }

            return undefined;
        };

        $scope.selectItem = function (item, feed) {
            if (feed === undefined) {
                feed = $scope.getCurrentFeed(item);
            }
            for (var i = 0; i < $scope.items.length; i++) {
                var it = $scope.items[i];
                if (item.id == it.id) {
                    it.selected = true;
                    selectedItemIndex = i;
                    if (item.read !== true) {
                        markItemAsRead(item, feed);
                    }
                } else {
                    it.selected = undefined;
                }
            }
        };

        var markItemAsRead = function (item, feed) {
            // TODO may not necessary if we are displaying all items (including those already read)

            $http.post('/rest/feeds/markAsRead', {itemId: item.id})
                .then(function () {
                    item.read = true;

                    if (feed.unread > 0) {
                        feed.unread--;
                    } else {
                        console.log("oops, feed[" + feed.id + "].unread=" + feed.unread);
                    }

                    var parentFolder = findParentFolder(feed);
                    if (parentFolder != null) {
                        if (parentFolder.unread > 0) {
                            parentFolder.unread--;
                        } else {
                            console.log("oops, folder[" + parentFolder.id + "].unread=" + parentFolder.unread);
                        }
                    }

                    if ($scope.totalUnread > 0) {
                        $scope.totalUnread--;
                    } else {
                        console.log("oops, $scope.totalUnread=" + $scope.totalUnread);
                    }

                    updateTitle();
                }, onError);
        };

        var findParentFolder = function (feed) {
            for (var i = 0; i < $scope.folders.length; i++) {
                for (var j = 0; j < $scope.folders[i].feeds.length; j++) {
                    if ($scope.folders[i].feeds[j].id == feed.id) {
                        return $scope.folders[i];
                    }
                }
            }
        };

        Mousetrap.bind('j', function () {
            $scope.$apply($scope.selectNextItem);
        });

        $scope.selectNextItem = function () {
            if (selectedItemIndex != -1) {
                if (selectedItemIndex < $scope.items.length - 1) {
                    $scope.selectItem($scope.items[selectedItemIndex + 1]);
                } else {
                    $scope.displayUnreadItems($scope.selectedFeed, true);
                }
            } else if ($scope.items.length > 0) {
                $scope.selectItem($scope.items[0]);
            } else if ($scope.totalUnread > 0) {
                $scope.displayUnreadItems($scope.selectedFeed, true);
            }
        };

        Mousetrap.bind('k', function () {
            $scope.$apply($scope.selectPreviousItem);
        });

        $scope.selectPreviousItem = function () {
            if (selectedItemIndex > 0) {
                $scope.selectItem($scope.items[selectedItemIndex - 1]);
            }
        };

        Mousetrap.bind('r', updateFeeds);

        Mousetrap.bind('o', function() {
            if (selectedItemIndex != -1) {
                var item = $scope.items[selectedItemIndex];
                window.open(item.url, '_blank');
            }
        });

        $scope.offset = AppHelpers.offset;
    }
]);
