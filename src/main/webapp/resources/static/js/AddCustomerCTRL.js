app.controller("addCustomerCtrl", function($scope, $http, $state) {
  $scope.name = "Yogendra";
  let arr = [];
    $scope.addDone = () => {
      arr.push($scope.address1);
      arr.push($scope.address2);
      $scope.personDetails.address = arr;
      console.log("personDetails "+$scope.personDetails);
      console.log("Inside addDone " + JSON.stringify($scope.personDetails));

    $http.post("/CRM/addingCustomer", JSON.stringify($scope.personDetails)).then((data) => {
      console.log("** " + JSON.stringify(data.data));
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
