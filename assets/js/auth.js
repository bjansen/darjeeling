darjeelingApp.controller('AuthCtrl', function ($scope, $http) {

    var onSignIn = function(googleUser) {
		var profile = googleUser.getBasicProfile();
		$scope.$apply(function () {
			$scope.userProfile = {
				id: profile.getId(),
				name: profile.getName(),
				imageUrl: profile.getImageUrl(),
				email: profile.getEmail()
			};
		});
	};

	window.onSignIn = onSignIn;

    $scope.signOut = function() {
    	$http.get('/rest/auth/logout').then(function (response) {
    		if (response.data === true) {
    			gapi.auth2.getAuthInstance().signOut();
    			document.location.reload(true);
    		}
    	});
    };
    
});
