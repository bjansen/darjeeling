var darjeelingApp = angular.module('darjeelingApp', []);

darjeelingApp.controller('FeedsCtrl', function($scope, $http) {
	$http.get('/rest/feeds/all').success(function(data) {
		$scope.feeds = data;
	});
});