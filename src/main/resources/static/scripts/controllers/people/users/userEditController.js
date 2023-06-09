app.controller('userEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        //check in user-interface Layer (client side).
        if ($scope.data.lastName === undefined || $scope.data.lastName == null || $scope.data.lastName === "") {
            Swal.fire('Please enter lastName!!');
            return;
        }
        if ($scope.data.email === undefined || $scope.data.email == null || $scope.data.email === "") {
            Swal.fire('Please enter password!!');
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire('Please set enable!!');
            return;
        }

        apiHandler.callPut("user/", $scope.data, (response) => {
            $scope.changeMenu("user-list");
        }, (error) => {
        }, true);
    };

//better is give data from database because Maybe someone else is changing the data.
    $scope.getData = () => {
        apiHandler.callGet("user/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});