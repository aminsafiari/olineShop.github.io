app.controller('invoiceDetailsCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.dataId = 0;
    $scope.data = {};
    $scope.totalPrice = 0;
    $scope.totalCount = 0;

    $scope.getData = () => {
        let url = "invoice/getInfo/" + $scope.dataId;
        apiHandler.callGet(url, (response) => {
            debugger;
            $scope.data = response.dataList[0];
            for (let i = 0; i < $scope.data.orderItem.length; i++) {
                $scope.totalCount += $scope.data.orderItem[i].count;
                $scope.totalPrice += ($scope.data.orderItem[i].price * $scope.data.orderItem[i].count);
            }
        }, (error) => {
        }, true);
    };

    $scope.init = () => {
        $scope.dataId = $rootScope.invoiceId;
        $scope.getData();
    }

    $scope.init();

});