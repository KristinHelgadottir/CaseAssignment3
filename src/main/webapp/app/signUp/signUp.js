'use strict';

angular.module('myApp.signUp', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/signUp', {
                    templateUrl: 'app/signUp/signUp.html'
                });
            }])

        .controller('signUpCtrl', function ($scope, $http) {
            $scope.user = {};

            $scope.signUp = function () {
                var data = $.param({
                    json: JSON.stringify({
                    userName : $scope.user.userName,
                    password: $scope.user.password})
                });
            };
            $http.post('/api/signUp', data).success(function(data){
                $scope.PostResponseDetail = data;
            });
        });