'use strict';

angular.module('myApp.signUp', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signUp', {
    templateUrl: 'app/home/home.html'
  });
}]);

