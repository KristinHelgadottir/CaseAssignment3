'use strict';

angular.module('myApp.calculator', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider)
            {
                $routeProvider.when('/calculator',
                        {
                            templateUrl: 'app/calculator/calculator.html',
                            controller: 'calculatorCtrl'
                        });

            }])
        .controller('calculatorCtrl', function () {

        });
