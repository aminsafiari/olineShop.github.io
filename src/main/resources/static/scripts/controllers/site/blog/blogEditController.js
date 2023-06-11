app.controller('blogEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        if ($rootScope.uploadedFile !== undefined && $rootScope.uploadedFile !== null
            && $rootScope.uploadedFile !== "") {
            $scope.data.image = $rootScope.uploadedFile;
        }

        if ($scope.data.title === undefined || $scope.data.title == null || $scope.data.title === "") {
            Swal.fire('Please enter title!!');
            return;
        }
        if ($scope.data.subtitle === undefined || $scope.data.subtitle == null || $scope.data.subtitle === "") {
            Swal.fire('Please enter subtitle!!');
            return;
        }
        if ($scope.data.description === undefined || $scope.data.description == null || $scope.data.description === "") {
            Swal.fire('Please enter description!!');
            return;
        }
        if ($scope.data.status === undefined || $scope.data.status == null) {
            Swal.fire('Please set status!!');
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === "") {
            Swal.fire('Please upload an image!!');
            return;
        }

        apiHandler.callPut("blog/", $scope.data, (response) => {
            $scope.changeMenu("blog-list");
        }, (error) => {
        }, true);
    };

    $scope.getData = () => {
        apiHandler.callGet("blog/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    };

    $scope.getData();

});