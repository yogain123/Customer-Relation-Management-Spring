app.controller("homeCtrl", function($scope, $http, $state, $timeout) {

  console.log("inside Home Controller controller");

  $scope.update = function(item) {
    $scope.fakeData = [];
    $state.current.params = item;
    $state.go('/updateCustomer', {
      reload: false
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

  $scope.search = () => {


    console.log("inside gettingSearchedCustomer with id "+$scope.customerId);

    let url = "/CRM/gettingSearchedCustomer/"+$scope.customerId;
    $http.get(url).then((data) => {

      console.log("gettingSearchedCustomer "+JSON.stringify(data.data));
      $scope.item = data.data;
      console.log("success");
    },() => {
      console.log("Error");
    });

  };

  $scope.searchWithName = () => {

    console.log("inside searchWithName");
    let url = "/CRM/gettingSearchedCustomerWithName/"+$scope.customerFirstName;
    $http.get(url).then((data) => {

      console.log("gettingSearchedCustomer "+JSON.stringify(data.data));
      $scope.searchedData = data.data;
      console.log("success");
    },() => {
      console.log("Error");
    });



  };

  $scope.uploadFile = function(files) {
      //Take the first selected file

      console.dir("filessss "+JSON.stringify(files));
      let file = files[0];
      let name = file.name;
      console.log("name "+name);
      $http.post("/CRM/file",file).then(() => {
        console.log("success");
      },() => {
        console.log("Error");
      });

  };

  $scope.init = () => {

    console.log("LOL "+JSON.stringify($state.get("/addCustomer").params));
    console.log("inside init***");
    $http.get("/CRM/gettingAllCustomer").then((data) => {
      $scope.fakeData = data.data;
      console.log($scope.fakeData);
      console.log("Success");

    }, () => {
      console.log("Error");
    });
  };
  console.log("before Init**");


  $scope.init();

});
