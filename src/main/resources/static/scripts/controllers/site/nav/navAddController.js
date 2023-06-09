app.controller('navAddCtrl', function ($scope, apiHandler) {

    $scope.data = {};

    $scope.addData = () => {

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

        apiHandler.callPost("nav/", $scope.data, (response) => {
             $scope.changeMenu("nav-list");
        }, (error) => {
        }, true);
    };

});