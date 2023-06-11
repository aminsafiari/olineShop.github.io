app.controller("dashboardCtrl", function ($scope, apiHandler) {

    $scope.data = {};

    $scope.getData = () => {
        apiHandler.callGet('dashboard', (response) => {
            debugger;
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true)
    }

    $scope.getData();

});