app.controller("navCtrl", function ($scope, mainApiHandler, $rootScope) {

    //array object from navigation list in database.
    $scope.navList = [];
    //for search product.
    $scope.searchKey = "";
    //for make active nav Title.
    $scope.page = $rootScope.page;

    $scope.getNavData = () => {
        mainApiHandler.callGet("nav/", (response) => {
            $scope.navList = response.dataList;
        });
    }

    $scope.search = () => {
        location.href = "/product/search?key=" + $scope.searchKey;
    }

    $scope.getNavData();

});