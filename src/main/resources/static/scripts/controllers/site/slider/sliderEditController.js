app.controller('sliderEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        if ($rootScope.uploadedFile !== undefined && $rootScope.uploadedFile !== null
            && $rootScope.uploadedFile !== "") {
            $scope.data.image = $rootScope.uploadedFile;
        }

        //check in user-interface Layer (client side).
        if ($scope.data.title === undefined || $scope.data.title == null || $scope.data.title === "") {
            Swal.fire('Please enter title!!');
            return;
        }
        if ($scope.data.link === undefined || $scope.data.link == null || $scope.data.link === "") {
            Swal.fire('Please enter link!!');
            return;
        }

        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire('Please set enable!!');
            return;
        }

        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === "") {
            Swal.fire('Please upload an image!!');
            return;
        }

        apiHandler.callPut("slider/", $scope.data, (response) => {
            $scope.changeMenu("slider-list");
        }, (error) => {
        }, true);
    };

//better is give data from database because Maybe someone else is changing the data.
    $scope.getData = () => {
        apiHandler.callGet("slider/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});