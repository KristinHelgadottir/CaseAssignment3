'use strict';

angular.module('myApp.allUsers', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) 
  {
    $routeProvider.when('/allUsers', 
    {
      templateUrl: 'app/allUsers/allUsers.html',
      controller: 'AllUsersCtrl',
      controllerAs : 'ctrl'
    });
}])

.controller('AllUsers', ["InfoFactory","InfoService",function(InfoFactory,InfoService) 
{
  this.msgFromFactory = InfoFactory.getInfo();
  this.msgFromService = InfoService.getInfo();
}]);