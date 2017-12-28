var app = angular.module("myApp", ['ui.router']);
app.config(function($urlRouterProvider, $stateProvider) {

  console.log("inside config");
  $urlRouterProvider.otherwise('/');
  $stateProvider
    .state('/addCustomer', {
      url: "/addCustomer",
      params: {},
      templateUrl: "resources/static/html/addCustomer.html",
      controller: "addCustomerCtrl"
    })
    .state('/updateCustomer', {
      url: "/updateCustomer",
      params: {},
      templateUrl: "resources/static/html/updateCustomer.html",
      controller: "updateCustomerCtrl"
    })
    .state('/', {
      url: "/",
      params: {},
      templateUrl: "resources/static/html/home.html",
      controller: "homeCtrl"
    });
});