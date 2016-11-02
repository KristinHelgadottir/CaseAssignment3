'use strict';

angular.module('myApp.exchangeRateInfo', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/exchangeRateInfo', {
                    templateUrl: 'app/exchangeRateInfo/exchangeRateInfo.html',
                    controller: 'exchangeRateInfoCtrl'
                });
            }])

        .controller('exchangeRateInfoCtrl', function ($http, $scope)
        {
            $http({
                method: 'GET',
                url: 'api/demouser'
            }).then(function successCallback(res)
            {
                $scope.data = res.data.message;
            }, function errorCallback(res)
            {
                $scope.error = res.status + ": " + res.data.statusText;
            });

        });