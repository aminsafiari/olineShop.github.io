app.controller('colorEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        //check in user-interface Layer (client side).
        if ($scope.data.name === undefined || $scope.data.name == null || $scope.data.name === "") {
            Swal.fire('Please enter name!!');
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value == null || $scope.data.value === "") {
            Swal.fire('Please set color!!');
            return;
        }

        apiHandler.callPut("color/", $scope.data, (response) => {
            $scope.changeMenu("color-list");
        }, (error) => {
        }, true);
    };

//better is give data from database because Maybe someone else is changing the data.
    $scope.getData = () => {
        apiHandler.callGet("color/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});