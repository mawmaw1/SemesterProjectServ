
var app = angular.module('myApp.view2', ['ngRoute', 'ui.bootstrap']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'app/view2/view2.html',
            controller: 'View2Ctrl',
            controllerAs: 'ctrl'
        });

    }]);

app.controller('View2Ctrl', ['GetFactory', '$http', function (GetFactory, $http) {
        var self = this;

        self.getAllFlightsFromDate = (function (from, to, date, persons) {
            var fixedDate = new Date(date);

            var jsonDate = fixedDate.toISOString();
            if (to !== from) {

                if (to === undefined) {


                    GetFactory.getAllFlightsFromDate(from, to, jsonDate, persons).then(function successCallback(res) {
                        self.data = res.data;

                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                } else {
                    GetFactory.getAllFlightsFromToDate(from, to, jsonDate, persons).then(function successCallback(res) {
                        self.data = res.data;

                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                }
            } else {
                alert("You have chosen the same airport for Origin and Destination");
            }
        });




    }]);

app.factory('GetFactory', ['$http', function ($http) {
        var self = this;

        var getAllFlightsFromDate = (function (from, to, date, persons) {
            return getAllFlightsFromDate =
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/SemesterProjectServer/api/data/' + from + '/' + date + '/' + persons

                    });
        });
        var getAllFlightsFromToDate = (function (from, to, date, persons) {
            return getAllFlightsFromToDate =
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/SemesterProjectServer/api/data/' + from + '/' + to + '/' + date + '/' + persons

                    });
        });
        return {
            getAllFlightsFromDate: getAllFlightsFromDate,
            getAllFlightsFromToDate: getAllFlightsFromToDate

        };

    }]);