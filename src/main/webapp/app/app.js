'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngAnimate',
  'angular-jwt',
  'ui.bootstrap',
  'myApp.security',
  'myApp.home',
  'myApp.documentation',
  'myApp.companyInfo',
  'myApp.exchangeRateInfo',
  'myApp.allUsers',
  'myApp.login',
  'myApp.filters',
  'myApp.directives',
  'myApp.factories',
  'myApp.services'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/home'});
}]).
config(function ($httpProvider) {
   $httpProvider.interceptors.push('AuthInterceptor');
});


