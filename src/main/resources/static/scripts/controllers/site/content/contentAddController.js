//'textAngularManager' add for htmlEditor.
//[$scope', 'apiHandler', 'textAngularManager', ... ; should sort by function sort, this important.
app.controller('contentAddCtrl', ['$scope', 'apiHandler', 'textAngularManager', function ($scope, apiHandler, textAngularManager) {

    $scope.data = {};

    $scope.addData = () => {

        //check in user-interface Layer (client side).
        if ($scope.data.key === undefined || $scope.data.key == null || $scope.data.key === "") {
            Swal.fire('Please enter key!!');
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value == null || $scope.data.value === "") {
            Swal.fire('Please enter value!!');
            return;
        }

        apiHandler.callPost("content/", $scope.data, (response) => {
            $scope.changeMenu("content-list");
        }, (error) => {
        }, true);
    };

}]);