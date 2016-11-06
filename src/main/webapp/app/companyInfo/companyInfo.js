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
                 url: 'api/user'
                }).then(function (response) {
                    $scope.names = response.data.records;
                });
                //,function errorCallback(res)
                  //  {
                    //    $scope.error = res.status + ": " + res.data.statusText;
                    //});
                    //skipAuthentication: true;'
        });
      