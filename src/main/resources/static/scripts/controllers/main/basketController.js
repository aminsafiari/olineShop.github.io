app.controller("basketCtrl", function ($scope, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page = "Basket";

    //get data from cookies.
    $scope.dataList = [];
    $scope.totalPrice = 0;
    $scope.totalCount = 0;

    //get data for fill initial page.
    //fill from cookies.
    $scope.fillDataList = () => {
        //TODO: fill from cookies.
        if ($cookies.get("basket") === undefined ||
            $cookies.get("basket") === null) {
            $scope.dataList = [];
            return;
        }
        $scope.dataList = JSON.parse($cookies.get("basket"));
        for (let i = 0; i < $scope.dataList.length; i++) {
            $scope.totalCount += $scope.dataList[i].count;
            $scope.totalPrice += ($scope.dataList[i].price * $scope.dataList[i].count);
        }
    }

    $scope.removeItem = (data) => {
        //confirmation for delete or any think.i can use confirm javaScript(is simple) or use library <<sweetalert2>>.
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to remove this item from your basket?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#0979cf',
            cancelButtonColor: '#c90449',
            confirmButtonText: 'Yes, remove it!'
        }).then((result) => {
            if (result.isConfirmed) {
                for (let i = 0; i < $scope.dataList.length; i++) {
                    if ($scope.dataList[i].id == data.id) {
                        if ($scope.dataList[i].count > 1) {
                            $scope.dataList[i].count--;
                        } else {
                            $scope.dataList.splice(i, 1);
                        }
                    }
                }
                $cookies.put("basket", JSON.stringify($scope.dataList), {path: '/'});
                //(Refresh) this location.
                location.href = location.href;
            }
        });
    }

    $scope.fillDataList();

});