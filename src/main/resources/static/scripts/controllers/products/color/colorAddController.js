app.controller('colorAddCtrl', function ($scope, apiHandler) {

    $scope.data = {};

    $scope.addData = () => {

        //check in user-interface Layer (client side).
        if ($scope.data.name === undefined || $scope.data.name == null || $scope.data.name === "") {
            Swal.fire('Please enter name!!');
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value == null || $scope.data.value === "") {
            Swal.fire('Please set color!!');
            return;
        }

        apiHandler.callPost("color/", $scope.data, (response) => {
            $scope.changeMenu("color-list");
        }, (error) => {
        }, true);
    };

});