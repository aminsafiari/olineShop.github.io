app.controller("productCtrl", function ($scope, mainApiHandler) {

    //array object from product list in database.
    $scope.newProductList = [];
    $scope.popularProductList = [];

    //we need api that return example 6-new product.
    $scope.getNewProductData = () => {
        mainApiHandler.callGet("product/newProducts/", (response) => {
            $scope.newProductList = response.dataList;
        });
    }

    $scope.getPopularProductData = () => {
        mainApiHandler.callGet("product/popularProducts/", (response) => {
            $scope.popularProductList = response.dataList;
        });
    }

    $scope.getNewProductData();
    $scope.getPopularProductData();

});