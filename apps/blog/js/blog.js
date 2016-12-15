var app = angular.module("blog", []);

app.directive("blogDirective", function() {
    return {
        templateUrl : 'apps/blog/html/blog.html',
        scope: {}
        //controller: blogController
    };
});

app.controller('blogController', function($scope, $http, BlogFactory, $window) {
	vm = this;
    $scope.name = "Christopher";
    $scope.lastname = "Doe";
    $scope.blogData = {
    	response: null
    };

    $scope.articles;

    BlogFactory.get().success(function (msg) {
        $scope.articles = msg.Articles;
        console.log($scope.articles);
    });


    

   //  getData = function() {
   //  	$http.get('/apps/blog/js/blogdata.json').success(function(response) { 
   //  		console.log("success!");
   //  		$scope.test = response;
   //      	console.log($scope.test);
   //  	});
   // }

   // getData();


   // $scope.name = blogService.getData();
   // console.log("trouble"); 
    
});


// app.service('blogService', function($http) {

// 	blogService = this

// 	var test = {
// 		data: null
// 	};

//     blogService.getData = function() {
//     	$http.get('/apps/blog/js/blogdata.json').success(function(response) { 
//     		console.log("success!");
//     		test.data = response.Articles[0].author;
//         	console.log(test.data);
//         	console.log("bottom of http");
        	
//     	})
//     	.error(function() {
//     		defer.reject('could not find someFile.json');
//     	});

//     	return test.data;
//    }
// });

app.factory('BlogFactory', function ($http) {
    return {
        get: function () {
            console.log("inside function");
            return $http.get('/apps/blog/js/blogdata.json');
        }
    };
});


