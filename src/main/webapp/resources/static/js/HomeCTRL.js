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

  $scope.uploadFile = function() {
      //Take the first selected file

      //console.log($scope.files.length);
      //console.log(count);
      //console.log("inside for");
      let obj = {};
      console.log($scope.files);
      let file = $scope.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file._file);
      reader.onload = function () {
      obj.name = file.name;
      obj.content = reader.result;
      console.log(obj);

      $http.post("/CRM/file",obj).then(() => {
        console.log("success ");
        $('#modal').modal({backdrop: 'static'});
        $('#modal').modal({keyboard: false});
        $('#modal').modal('show');
      },() => {
        console.log("Error");
      });
      };
      reader.onerror = function (error) {
          console.log('Error: ', error);
      };

  };

  $scope.searchCustomerImage = function(){


    let url = "/CRM/searchImageWithName/"+$scope.customerImage;

    $http.get(url).then((data) => {

      $scope.imageData = data.data;
      console.log("image DATA is "+JSON.stringify($scope.imageData));
      console.log("success");
      if($scope.imageData=="" || $scope.imageData==undefined || $scope.imageData==null)
            $scope.check = false;
      else {
          $scope.check = true;
      }
    },() => {
      $scope.check = false;
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

 $scope.check = false;
  $scope.init();





  // function getBase64(file) {
  //    var reader = new FileReader();
  //    reader.readAsDataURL(file);
  //    reader.onload = function () {
  //       return reader.result;
  //    };
  //    reader.onerror = function (error) {
  //      console.log('Error: ', error);
  //    };
  // }

});

app.directive('ngFileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.ngFileModel);
            var isMultiple = attrs.multiple;
            var modelSetter = model.assign;
            element.bind('change', function () {
                var values = [];
                angular.forEach(element[0].files, function (item) {
                    var value = {
                       // File Name
                        name: item.name,
                        //File Size
                        size: item.size,
                        //File URL to view
                        url: URL.createObjectURL(item),
                        // File Input Value
                        _file: item
                    };
                    values.push(value);
                });
                scope.$apply(function () {
                    if (isMultiple) {
                        modelSetter(scope, values);
                    } else {
                        modelSetter(scope, values[0]);
                    }
                });
            });
        }
    };
}]);
