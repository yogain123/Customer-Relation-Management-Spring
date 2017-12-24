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
