app.controller('sliderAddCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};

    $scope.addData = () => {
        $scope.data.image = $rootScope.uploadedFile;

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

        apiHandler.callPost("slider/", $scope.data, (response) => {
            $scope.changeMenu("slider-list");
        }, (error) => {
        }, true);
    };

});