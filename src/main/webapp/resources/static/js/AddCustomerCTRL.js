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
