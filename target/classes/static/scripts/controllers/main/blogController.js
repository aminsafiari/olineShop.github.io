app.controller("blogCtrl", function ($scope, mainApiHandler, $rootScope) {

    //for make active nav Title.
    $rootScope.page = "Blog";

    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };

    //array object from blog list in database.
    $scope.blogList = [];
    $scope.pageCount = 0;
    $scope.totalCount = 0;

    $scope.getBlogData = () => {
        let url = "blog/getAllData?pageSize=" + $scope.query.pageSize +
            "&pageNumber=" + $scope.query.pageNumber;
        mainApiHandler.callGet(url, (response) => {
            $scope.blogList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.query.pageSize > 0)
                $scope.pageCount++;
        });
    }

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getBlogData();
    };

    $scope.range = (max) => {
        return new Array(max);
    };

    $scope.getBlogData();
});