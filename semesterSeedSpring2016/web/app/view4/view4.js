'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view4', {
    templateUrl: 'app/view4/view4.html',
    controller: 'View4Ctrl',
    controllerAs : 'ctrl'
  });
}])

.controller('View4Ctrl', ["InfoFactory","InfoService",function(InfoFactory,InfoService) {
  this.msgFromFactory = InfoFactory.getInfo();
  this.msgFromService = InfoService.getInfo();
}]);