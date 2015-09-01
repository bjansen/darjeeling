var darjeelingApp = angular.module('darjeelingApp', ['ngMaterial']);

darjeelingApp.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('indigo')
        .accentPalette('purple');
});

darjeelingApp.directive('href', function () {
    return {
        compile: function (obj) {
            var url = obj[0].href;

            if (!url.match(/^mailto\:/)
                && (url != location.hostname)
                && !url.match(/^javascript\:/)
                && !url.match(/^$/)) {
                obj.attr('target', '_blank');
            }
        }
    };
});

darjeelingApp.controller('FeedsCtrl', function ($scope, $http, $mdSidenav, $mdDialog) {

    setupHttp($http);

    var updateFeeds = function () {
        $http.get('/rest/feeds/all').success(function (data) {
            $scope.folders = data.filter(function (el) {
                return el['feeds'] !== undefined;
            });
            $scope.feeds = data.filter(function (el) {
                return el['feeds'] === undefined;
            });

            $scope.displayUnreadItems($scope.selectedFeed);
        });
    };

    updateFeeds();

    $scope.items = [];
    $scope.prefs = {
        showUnreadOnly: true
    };

    // TODO use prefs.showUnreadOnly
    $scope.displayUnreadItems = function (feedOrFolder) {
        if ($scope.folders === undefined) {
            return; // we're not finished loading feeds
        }
        var request;

        if (feedOrFolder === '-1') {
            request = $http.get('/rest/feeds/items?unreadOnly=' + $scope.prefs.showUnreadOnly);
        } else if (feedOrFolder.feeds !== undefined) {
            request = $http.get('/rest/feeds/itemsInFolder?folderId=' + feedOrFolder.id + '&unreadOnly=' + $scope.prefs.showUnreadOnly);
        } else {
            request = $http.get('/rest/feeds/items?feedId=' + feedOrFolder.id + '&unreadOnly=' + $scope.prefs.showUnreadOnly);
        }
        request.success(
            function (data) {
                $scope.items = data;
            });
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

    $scope.showSubscribePopup = function (ev) {
        $mdDialog.show({
            controller: SubscriptionController,
            templateUrl: 'subscribePopup.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            locals: {
                folders: $scope.folders
            }
        }).then(function (result) {
                $http.post('/rest/feeds/subscribe', {title: result.name, url: result.url, folderId: result.folderId})
                    .then(function () {
                        updateFeeds();
                    }, function () {
                        console.log("ko");
                    });
            }
        );
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
});

darjeelingApp.filter('removeSpam', function () {
    return function (string) {
        var thingsToRemove = [];
        var doc = document.implementation.createHTMLDocument("");
        doc.documentElement.innerHTML = string;

        thingsToRemove = thingsToRemove.concat([].slice.call(doc.getElementsByClassName('feedflare')));

        for (var i = 0; i < thingsToRemove.length; i++) {
            thingsToRemove[i].parentNode.removeChild(thingsToRemove[i]);
        }

        return doc.documentElement.innerHTML;
    };
});

darjeelingApp.filter('trust', function ($sce) {
    return function (raw) {
        return $sce.trustAsHtml(raw);
    };
});

darjeelingApp.filter('prettyDate', function () {
    var msPerMinute = 1000 * 60;
    var msPerHour = 1000 * 60 * 60;
    var msPerDay = 1000 * 60 * 60 * 24;

    return function (rawDate) {
        var date = new Date(rawDate);
        var dateDiff = new Date() - date;

        if (dateDiff / msPerMinute < 60) {
            return Math.floor(dateDiff / msPerMinute) + " minutes ago";
        } else if (dateDiff / msPerHour < 24) {
            return Math.floor(dateDiff / msPerHour) + " hours ago";
        } else if (dateDiff / msPerDay < 4) {
            return Math.floor(dateDiff / msPerDay) + " days ago";
        }

        return date.toDateString();
    };
});

function SubscriptionController($scope, $mdDialog, folders) {
    $scope.folders = folders;
    $scope.hide = function () {
        $mdDialog.hide();
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
    $scope.subscribe = function (form) {
        if (form.$valid) {
            var folderId = ($scope.folder === undefined) ? undefined : $scope.folder.id;
            $mdDialog.hide({name: $scope.name, url: $scope.url, folderId: folderId});
        }
    };
}

setupHttp = function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

    var param = function (obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

        for (name in obj) {
            value = obj[name];

            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }

        return query.length ? query.substr(0, query.length - 1) : query;
    };

    $httpProvider.defaults.transformRequest = [function (data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];
};
