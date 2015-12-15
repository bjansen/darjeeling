darjeelingApp.controller('SubscriptionsController', ['$scope', '$mdDialog', 'customHttp',
    function ($scope, $mdDialog, customHttp) {
        var $http = customHttp.getHttp();

        $scope.showSubscribePopup = function (ev, folders) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'subscribePopup.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                locals: {
                    folders: folders,
                    feeds: undefined,
                    editMode: false
                }
            }).then(function (sub) {
                    var folderId = (sub.folder === undefined) ? undefined : sub.folder.id;
                    $http.post('/rest/feeds/subscribe', {
                            title: sub.name,
                            url: sub.url,
                            folderId: folderId
                        })
                        .then(function () {
                            $scope.$emit('feedAdded');
                        }, function () {
                            console.log("ko");
                        });
                }
            );
        };

        var DialogController = function ($scope, $mdDialog, folders, feeds, editMode) {
            $scope.folders = folders;
            $scope.sub = {};
            $scope.feeds = feeds;
            $scope.editMode = editMode;
            if (editMode) {
                $scope.sub.name = feeds[0].name;
                $scope.sub.url = feeds[0].url;
                $scope.sub.feed = feeds[0];
                $scope.sub.folder = findParentFolder(feeds[0].id, folders);
                $scope.title = "Edit subscriptions";
            } else {
                $scope.title = "Subscribe to new feed";
            }

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.subscribe = function (form) {
                if (form.$valid) {
                    $mdDialog.hide($scope.sub);
                }
            };
        };

        $scope.showEditSubscriptionPopup = function (ev, folders, feeds) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'subscribePopup.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                locals: {
                    folders: folders,
                    feeds: flattenFeeds(folders, feeds),
                    editMode: true
                }
            }).then(function (sub) {
                    var folderId = (sub.folder === undefined) ? undefined : sub.folder.id;
                    $http.post('/rest/feeds/edit/' + sub.feed.id, {
                            title: sub.name,
                            url: sub.url,
                            folderId: folderId
                        })
                        .then(function () {
                            $scope.$emit('feedAdded');
                        }, function () {
                            console.log("ko");
                        });
                }
            );
        };

        var findParentFolder = function (feedId, folders) {
            for (var i = 0; i < folders.length; i++) {
                for (var j = 0; j < folders[i].feeds.length; j++) {
                    if (folders[i].feeds[j].id == feedId) {
                        return folders[i];
                    }
                }
            }
        };

        $scope.updateValues = function (newFeed) {
            $scope.sub.name = newFeed.name;
            $scope.sub.url = newFeed.url;
            $scope.sub.folder = findParentFolder(newFeed.id, $scope.folders);
        };

        var flattenFeeds = function (folders, feeds) {
            var result = [];
            for (var i = 0; i < folders.length; i++) {
                result = result.concat(folders[i].feeds);
            }
            return result.concat(feeds);
        }
    }
]);
