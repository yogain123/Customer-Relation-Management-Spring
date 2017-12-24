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
