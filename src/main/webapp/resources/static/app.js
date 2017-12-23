var app = angular.module("myApp", ['ui.router']);
app.config(function($urlRouterProvider, $stateProvider) {

  console.log("inside config");
  $urlRouterProvider.otherwise('/');
  $stateProvider
    .state('/addCustomer', {
      url: "/addCustomer",
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

app.controller("homeCtrl", function($scope, $http, $state, $rootScope, $timeout) {

  console.log("inside controller");

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
    }, () => {
      console.log("ErrorDelete");
    });

    $scope.init();

  };


  $scope.init = () => {

    $timeout(() => {
      $http.get("/CRM/gettingAllCustomer").then((data) => {
        $scope.fakeData = data.data;
        console.log($scope.fakeData);
        console.log("Success");

      }, () => {
        console.log("Error");
      });


    }, 40);



  };

  $scope.init();
});


app.controller("addCustomerCtrl", function($scope, $http, $state) {
  $scope.name = "Yogendra";
  $scope.addDone = () => {
    console.log("Inside addDone " + JSON.stringify($scope.personDetails));

    $http.post("/CRM/addingCustomer", $scope.personDetails).then(() => {
      console.log("Success");

    }, () => {
      console.log("Error");
    });

    $state.go('/', {
      reload: true
    });

  };
});

app.controller("updateCustomerCtrl", function($scope, $state, $http) {
  $scope.personDetails = $state.get("/").params;
  $scope.name = "Yogendra";
  $scope.updateDone = (item) => {

    var url = "/CRM/updatingCustomer/"+item.id;

    $http.put(url,$scope.personDetails).then(() => {
        console.log("Success");
    },() => {
      console.log("Error");
    });

    $state.go('/', {
      reload: false
    });

  };
  $scope.$watch("personDetails.firstName", function(newValue, oldValue) {
    console.log(oldValue + " " + newValue);
    console.log("hol");
  });
});
