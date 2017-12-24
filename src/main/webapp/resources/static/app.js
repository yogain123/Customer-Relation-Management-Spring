var app = angular.module("myApp", ['ui.router']);
app.config(function($urlRouterProvider, $stateProvider) {

  console.log("inside config");
  $urlRouterProvider.otherwise('/');
  $stateProvider
    .state('/addCustomer', {
      url: "/addCustomer",
      params: {},
      templateUrl: "resources/static/addCustomer.html",
      controller: "addCustomerCtrl"
    })
    .state('/updateCustomer', {
      url: "/updateCustomer",
      params: {},
      templateUrl: "resources/static/updateCustomer.html",
      controller: "updateCustomerCtrl"
    })
    .state('/', {
      url: "/",
      params: {},
      templateUrl: "resources/static/home.html",
      controller: "homeCtrl"
    });
});

app.controller("homeCtrl", function($scope, $http, $state, $timeout) {

  console.log("inside Home Controller controller");

  $scope.update = function(item) {
    $scope.fakeData = [];
    $state.current.params = item;
    $state.go('/updateCustomer', {
      reload: true
    });
  };


  $scope.delete = (item) => {

    var url = "/CRM/deletingCustomer/" + item.id;
    $http.delete(url).then(() => {
      console.log("SuccessDelete");
      $scope.init();

    }, () => {
      console.log("ErrorDelete");
    });


  };

  $scope.init = () => {
    console.log("inside init");
    $http.get("/CRM/gettingAllCustomer").then((data) => {
      $scope.fakeData = data.data;
      console.log($scope.fakeData);
      console.log("Success");

    }, () => {
      console.log("Error");
    });
  };

  $scope.init();

});


app.controller("addCustomerCtrl", function($scope, $http, $state) {
  $scope.name = "Yogendra";
  $scope.addDone = () => {
    //console.log("Inside addDone " + JSON.stringify($scope.personDetails));

    $http.post("/CRM/addingCustomer", $scope.personDetails).then((data) => {
      console.log("** " + data.data);
      $state.current.params = data.data; // Called via REStTemplate
      console.log("Ssssuccess");
      $state.go('/', {
        reload: false
      });

    }, () => {
      console.log("Error");
    });

  };
});

app.controller("updateCustomerCtrl", function($scope, $state, $http) {
  $scope.personDetails = $state.get("/").params;
  $scope.name = "Yogendra";
  $scope.updateDone = (item) => {

    var url = "/CRM/updatingCustomer/" + item.id;

    $http.put(url, $scope.personDetails).then(() => {
      console.log("Success");
      $state.go('/', {
        reload: false
      });

    }, () => {
      console.log("Error");
    });


  };
  $scope.$watch("personDetails.firstName", function(newValue, oldValue) {
    console.log(oldValue + " " + newValue);
    console.log("hol");
  });
});
