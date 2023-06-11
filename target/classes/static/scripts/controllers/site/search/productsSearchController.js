app.controller("productsSearchCtrl", function ($scope, mainApiHandler, $rootScope) {

    //For activate in list.
    $rootScope.page = "Products";
    //For give products with search.
    $scope.searchKey = "";
    //For get data from response.
    $scope.dataList = [];

    $scope.getSearchKeyProducts = () => {
        let url = "product/search?key=" + $scope.searchKey;
        mainApiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.searchKey = "";
        });
    }

    //For give products by search.
    $scope.initSearch = (key) => {
        $scope.searchKey = key;
        $scope.getSearchKeyProducts();
    }

});