
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
        
        
        
        self.getCompany = function (input, type) {
            if (type === "cvr") {
                self.getCvr(input);
            }
            if (type === "name") {
                self.getName(input);
            }
            if (type === "phone") {
                self.getPhone(input);
            }

        };
        self.getAllFlightsFromDate = (function (persons, date, from) {
            var fixedDate = new Date(date);

            var jsonDate = fixedDate.toISOString();

            

            GetFactory.getAllFlightsFromDate(persons, jsonDate, from).then(function successCallback(res) {
                self.data = res.data;

            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });

        self.getName = (function (name) {
            GetFactory.getName(name).then(function successCallback(res) {
                self.data = res.data;
                console.log(self.data);
            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });
        self.getPhone = (function (phone) {
            GetFactory.getPhone(phone).then(function successCallback(res) {
                self.data = res.data;
                console.log(self.data);
            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });



    }]);

app.factory('GetFactory', ['$http', function ($http) {
        var self = this;

        var getAllFlightsFromDate = (function (persons, date, from) {
            return getPhone =
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/SemesterProjectServer/api/data/' + from + '/' + date + '/' + persons



                    });
        });
        return {
            getAllFlightsFromDate: getAllFlightsFromDate

        };

    }]);