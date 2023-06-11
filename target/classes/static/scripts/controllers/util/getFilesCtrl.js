app.controller('getFilesController', function ($scope, $http, apiHandler) {

    $scope.allFiles = [];
    //for dont show empty Table in fileManager.
    $scope.showTable = false;

    $scope.getAllFiles = function () {

        // REST URL:
        let url = "utils/upload/getAllFiles";
        apiHandler.callGet(url, (response) => {
            $scope.allFiles = response.dataList;
            $scope.showTable = true;
        }, (error) => {

        }, true);
    };
});