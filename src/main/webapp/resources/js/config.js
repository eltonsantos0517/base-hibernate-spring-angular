app.config(function($routeProvider, $httpProvider, $locationProvider){
	
	$locationProvider.html5Mode(true);
	
    $routeProvider
	    .when('/', {
	        templateUrl : 'index.html',
	        controller : 'appController',
	        controllerAs: 'ctrl'
	      })
	      .when('/app', {
	        templateUrl : 'app.html',
	        controller : 'appController',
	        controllerAs: 'ctrl'
	      })
	      .otherwise({
	    	  redirectTo: "/"
	      });
    
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
})