var darjeelingApp = angular.module('darjeelingApp', []);

darjeelingApp.controller('FeedsCtrl', function($scope, $http) {
	$http.get('/rest/feeds/all').success(function(data) {
		$scope.feeds = data;
	});
	
	$scope.items = [];
	
	$scope.displayUnreadItems = function(feed) {
		$http.get('/rest/feeds/items?feedId=' + feed.id).success(function(data) {
			$scope.items = data;
		});
	}
});