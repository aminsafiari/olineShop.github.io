app.controller('userAddCtrl', function ($scope, apiHandler) {

        $scope.data = {};

        $scope.addData = () => {

            //check in user-interface Layer (client side).
            if ($scope.data.lastName === undefined || $scope.data.lastName == null || $scope.data.lastName === "") {
                Swal.fire('Please enter lastName!!');
                return;
            }
            if ($scope.data.username === undefined || $scope.data.username == null || $scope.data.username === "") {
                Swal.fire('Please enter username!!');
                return;
            }
            if ($scope.data.password === undefined || $scope.data.password == null || $scope.data.password === "") {
                Swal.fire('Please enter password!!');
                return;
            }
            if ($scope.data.email === undefined || $scope.data.email == null || $scope.data.email === "") {
                Swal.fire('Please enter password!!');
                return;
            }
            if ($scope.data.role === undefined || $scope.data.role == null) {
                Swal.fire('Please set role!!');
                return;
            }
            if ($scope.data.enable === undefined || $scope.data.enable == null) {
                Swal.fire('Please set enable!!');
                return;
            }

            apiHandler.callPost("user/", $scope.data, (response) => {
                $scope.changeMenu("user-list");
            }, (error) => {
            }, true);
        }
        ;

    }
);