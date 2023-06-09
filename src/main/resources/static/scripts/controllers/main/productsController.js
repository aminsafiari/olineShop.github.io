app.controller("productsCtrl", function ($scope, mainApiHandler, $rootScope) {

    //for action nav.
    $rootScope.page = "Products";
    //array object from product list in database.
    $scope.categoryList = [];
    //title show products.
    $scope.selectedTitle = '';
    //for action list group.
    $scope.selectedFilter = "";
    //get data (popular, new, cheapest,...) from database.
    $scope.productsList = [];

    $scope.getCategoryList = () => {
        mainApiHandler.callGet("productCategory/", (response) => {
            $scope.categoryList = response.dataList;
        });
    }

    //we need api that return example 6-new product.
    $scope.getNewProductData = () => {
        mainApiHandler.callGet("product/newProducts/", (response) => {
            $scope.productsList = response.dataList;
        });
    }

    $scope.getPopularProductData = () => {
        mainApiHandler.callGet("product/popularProducts/", (response) => {
            $scope.productsList = response.dataList;
        });
    }

    $scope.changeFilter = (filter) => {
        $scope.selectedFilter = filter;
        switch (filter) {
            case 'popular':
                $scope.selectedTitle = 'Popular Products';
                $scope.getPopularProductData();
                break;
            case 'new' :
                $scope.selectedTitle = 'New Products';
                $scope.getNewProductData();
                break;
            case 'cheapest':
                $scope.selectedTitle = 'Cheapest Products';
                break;
            case 'expensive':
                $scope.selectedTitle = 'Expensive Products';
                break;
        }
    }

    $scope.getCategoryList();
    $scope.changeFilter('popular')

});