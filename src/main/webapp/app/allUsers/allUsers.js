'use strict';

angular.module('myApp.allUsers', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider)
            {
                $routeProvider.when('/allUsers',
                        {
                            templateUrl: 'app/allUsers/allUsers.html',
                            controller: 'getAllCtrl'
                        });
            }])

        .controller('AllUsers', ["InfoFactory", "InfoService", function (InfoFactory, InfoService)
            {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }])

        .controller('getAllCtrl', function ($scope, $http) {
            
            $scope.users = [];
                    
                    $scope.getAll = function () {
                $http({
                    method: 'GET',
                    url: '/api/admin/users'
                }).then(function (response) {
                    $scope.users = response.data.records;
                });
            };
        });