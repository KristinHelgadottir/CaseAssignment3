'use strict';

angular.module('myApp.signUp', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/signUp', {
                    templateUrl: 'app/signUp/signUp.html'
                });
            }])

        .controller('signUpCtrl', function ($scope, $http) {
            $scope.user = {};
            $scope.data = {};
            $scope.signUp = function () {
                $scope.data = $.param({
                    json: JSON.stringify({
                    userName : $scope.user.userName,
                    password: $scope.user.password})
                });
            };
            $http.post('/seedMaven/api/all/signUp', $scope.data).success(function(data){
                
                $scope.PostResponseDetail = $scope.data;
            });
        });