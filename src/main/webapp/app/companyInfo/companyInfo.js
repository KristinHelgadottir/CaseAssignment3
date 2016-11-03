'use strict';

angular.module('myApp.companyInfo', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/companyInfo', {
                    templateUrl: 'app/companyInfo/companyInfo.html',
                    controller: 'companyInfoCtrl'
                });
            }])

        .controller('companyInfoCtrl', function ($http, $scope)
        {
            $http({
                 method: 'GET',
                 url: 'http://cvrapi.dk/api/search'
                }).then(function successCallback(res)
                    {
                        $scope.data = res.data.message;
                    }, function errorCallback(res)
                    {
                        $scope.error = res.status + ": " + res.data.statusText;
                    });
                    //skipAuthentication: true;'
        });
      