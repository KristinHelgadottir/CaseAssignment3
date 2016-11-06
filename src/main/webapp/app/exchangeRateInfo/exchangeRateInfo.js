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


//function get(json) {
//    var rate = json['rate'];
//}
//var script = document.createElement('script');
//script.src = 'http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesHistoryXML?lang=da'+
//    '&callback=get';
//document.head.appendChild(script);
