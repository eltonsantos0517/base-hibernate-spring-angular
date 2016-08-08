app.controller('appController', function($scope, $http){
			
	$scope.app = function(){
		console.log("Hello Angular!");
		
		$http({
			  method: 'GET',
			  url: '/api/1/app'
			}).then(function successCallback(response) {
				console.log(response);
			  }, function errorCallback(response) {
				  console.log(response);
			  });
	};
}) 
	
