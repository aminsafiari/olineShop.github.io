app.controller("productsCategoryCtrl", function ($scope, mainApiHandler, $rootScope) {

    //for make active nav Title.
    $rootScope.page = "Products";
    //For give blog id from server.
    $scope.dataId = 0;
    //For get data from response.
    $scope.dataList = {};
    $scope.category = {};

    $scope.query = {
        pageSize: 12,
        pageNumber: 0
    };

    $scope.pageCount = 0;
    $scope.totalCount = 0;

    $scope.getProductsByCategory = () => {
        let url = "product/getAll/" + $scope.dataId + "/?pageSize=" + $scope.query.pageSize +
            "&pageNumber=" + $scope.query.pageNumber;
        mainApiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.query.pageSize > 0)
                $scope.pageCount++;
        });
    }

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getProductsByCategory();
    };

    $scope.range = (max) => {
        return new Array(max);
    };

    //For give blog id in bolg.jsp then run getBlogInfo method.
    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getProductsByCategory();
    }

});