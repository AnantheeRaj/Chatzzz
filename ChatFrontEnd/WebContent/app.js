var app = angular.module('myApp', ['ngRoute', 'ngCookies']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'pages/home.html',
    controller  : 'HomeController'
  })
  .when('/chat', {
    templateUrl : 'c_chat/chat.html',
    controller  : 'ChatController'
  })
 
  .when('/list_blog', {
    templateUrl : 'c_blog/list_blog.html',
    controller  : 'BlogController'
  })
  .when('/view_blog', {
    templateUrl : 'c_blog/view_blog.html',
    controller  : 'BlogController'
  })
  .when('/create_blog', {
    templateUrl : 'c_blog/create_blog.html',
    controller  : 'BlogController'
  })
  .when('/post_job', {
    templateUrl : 'c_job/post_job.html',
    controller  : 'JobController'
  })
   .when('/search_job', {
    templateUrl : 'c_job/search_job.html',
    controller  : 'JobController'
  })
  .when('/login', {
    templateUrl : 'pages/login.html',
    controller  : 'UserController'
  })
  .when('/register', {
    templateUrl : 'pages/register.html',
    controller  : 'UserController'
  })
  .when('/search_friend', {
    templateUrl : 'pages/search_friend.html',
    controller  : 'FriendController'
  })
  .when('/view_friend', {
    templateUrl : 'pages/view_friend.html',
    controller  : 'FriendController'
  })


  /*.when('/logout', {
    templateUrl : 'pages/logout.html',
    controller  : 'UserController'
  })*/
  .when('/job', {
    templateUrl : 'c_job/job.html',
    controller  : 'JobController'
  })
 
  .when('/about', {
    templateUrl : 'pages/about.html',
    controller  : 'AboutController'
  })
  .otherwise({redirectTo: '/'});
});


app.run(function ($rootScope, $location, $cookieStore, $http){

	$rootScope.$on('$locationChangeStart', function(event, next, current){
	console.log("$locationChangeStart")
	//redirect to login page if not logged in and typing to access a restricted page

	var restrictedPage=$.inArray($location.path(), ['/login','/register']) ===-1;
	console.log("restrictedPage:" +restrictedPage)
	var loggedIn=$rootScope.currentUser.userid;
	console.log("loggedIn:"+loggedIn)
	if(restrictedPage & !loggedIn){
	console.log("Navigating to login page:")
	alert("You are not logged and so you can't do this operation")
	$location.path('/login');
	}
	else //logged in
	{
	
	 var role = $rootScope.currentUser.role;
	 var userRestrictedPage = $.inArray($location.path(), ["/create_blog"]) == 0;
	 
	 if(userRestrictedPage && role!='Admin' )
		 {
		 
		  alert("You can not do this operation as you are logged as : " + role )
		   $location.path('/login');
		 }
	     
	
	}

	});

	//keep user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if($rootScope.currentUser){
	$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser;
	
	}
	});