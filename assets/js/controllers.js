var darjeelingApp = angular.module('darjeelingApp', [ 'ui.bootstrap' ]);

darjeelingApp.controller('FeedsCtrl', function($scope, $http, $modal) {
	
	setupHttp($http);
	
	updateFeeds = function() {
		$http.get('/rest/feeds/all').success(function(data) {
			$scope.folders = data.filter(function(el) {
				return el['feeds'] !== undefined;
			});
			$scope.feeds = data.filter(function(el) {
				return el['feeds'] === undefined;
			});
		});
	};
	
	updateFeeds();

	$scope.items = [];

	$scope.displayUnreadItems = function(feed) {
		$http.get('/rest/feeds/items?feedId=' + feed.id).success(
				function(data) {
					$scope.items = data;
				});
	}

	$scope.toggle = function(folder) {
		if (folder.expanded === undefined) {
			folder.expanded = false;
		}
		folder.expanded = !folder.expanded;
	};

	$scope.showSubscribePopup = function() {
		var modalInstance = $modal.open({
			templateUrl : 'subscribePopup.html',
			controller : 'SubscriptionsCtrl',
			resolve : {
			}
		});
		
		modalInstance.result.then(function (result) {
			$http.post('/rest/feeds/subscribe', {title: result.name, url: result.url})
				 .then(function(response) {
					 console.log("ok");
					 console.log(response);
					 updateFeeds();
				 }, function(response) {
					 console.log("ko");
				 });
	    });
	}
});

darjeelingApp.controller('SubscriptionsCtrl', function($scope, $modalInstance) {
	$scope.url = "";
	$scope.name = "";
	
	$scope.ok = function() {
		$modalInstance.close({name: $scope.name, url: $scope.url});
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
});

setupHttp = function($httpProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

	var param = function(obj) {
	    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
	      
	    for(name in obj) {
	      value = obj[name];
	        
	      if(value instanceof Array) {
	        for(i=0; i<value.length; ++i) {
	          subValue = value[i];
	          fullSubName = name + '[' + i + ']';
	          innerObj = {};
	          innerObj[fullSubName] = subValue;
	          query += param(innerObj) + '&';
	        }
	      }
	      else if(value instanceof Object) {
	        for(subName in value) {
	          subValue = value[subName];
	          fullSubName = name + '[' + subName + ']';
	          innerObj = {};
	          innerObj[fullSubName] = subValue;
	          query += param(innerObj) + '&';
	        }
	      }
	      else if(value !== undefined && value !== null)
	        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
	    }
      
	    return query.length ? query.substr(0, query.length - 1) : query;
	};
		 
	$httpProvider.defaults.transformRequest = [function(data) {
	    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	}];
};
