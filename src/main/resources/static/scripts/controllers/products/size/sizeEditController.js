app.controller('sizeEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        //check in user-interface Layer (client side).
        if ($scope.data.title === undefined || $scope.data.title == null || $scope.data.title === "") {
            Swal.fire('Please enter title!!');
            return;
        }

        apiHandler.callPut("size/", $scope.data, (response) => {
            $scope.changeMenu("size-list");
        }, (error) => {
        }, true);
    };

//better is give data from database because Maybe someone else is changing the data.
    $scope.getData = () => {
        apiHandler.callGet("size/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});