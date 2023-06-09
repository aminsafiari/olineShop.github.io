app.controller('contentEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        //check in user-interface Layer (client side).
        if ($scope.data.key === undefined || $scope.data.key == null || $scope.data.key === "") {
            Swal.fire('Please enter key!!');
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value == null || $scope.data.value === "") {
            Swal.fire('Please enter value!!');
            return;
        }

        apiHandler.callPut("content/", $scope.data, (response) => {
            $scope.changeMenu("content-list");
        }, (error) => {
        }, true);
    };

//better is give data from database because Maybe someone else is changing the data.
    $scope.getData = () => {
        apiHandler.callGet("content/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});