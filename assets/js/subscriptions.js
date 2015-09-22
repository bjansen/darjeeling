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
                    folders: folders
                }
            }).then(function (result) {
                    $http.post('/rest/feeds/subscribe', {
                        title: result.name,
                        url: result.url,
                        folderId: result.folderId
                    })
                        .then(function () {
                            $scope.$emit('feedAdded');
                        }, function () {
                            console.log("ko");
                        });
                }
            );
        };

        var DialogController = function ($scope, $mdDialog, folders) {
            $scope.folders = folders;
            $scope.sub = {};

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.subscribe = function (form) {
                if (form.$valid) {
                    var folderId = ($scope.sub.folder === undefined) ? undefined : $scope.sub.folder.id;
                    $mdDialog.hide({name: $scope.sub.name, url: $scope.sub.url, folderId: folderId});
                }
            };
        }
    }
]);
