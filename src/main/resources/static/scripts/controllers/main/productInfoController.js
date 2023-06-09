app.controller("productInfoCtrl", function ($scope, mainApiHandler, $rootScope, $cookies) {

    //For activate in list.
    $rootScope.page = "Products";
    //For give product id from server.
    $scope.dataId = 0;
    //For get data from response.
    $scope.data = {};
    //For create order.
    $scope.orderItem = {};
    //Array list object from products in basket.
    $scope.orderItemList = [];

    $scope.getProductInfo = () => {
        let url = "product/info/" + $scope.dataId;
        mainApiHandler.callGet(url, (response) => {
            debugger;
            $scope.data = response.dataList[0];
            $scope.orderItem.colorId = $scope.data.colors[0];
        });
    }

    //For give product id in product.jsp then run getProductInfo method.
    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getProductInfo();
    }

    //handle add to basket with cookie.
    $scope.addToBasket = () => {
        if ($scope.orderItem.colorId === undefined ||
            $scope.orderItem.colorId === null ||
            $scope.orderItem.colorId === "" ||
            $scope.orderItem.colorId === 0) {
            Swal.fire({
                title: 'Warning',
                text: "Please select a Color",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#0979cf',
                cancelButtonColor: '#c9041e',
                confirmButtonText: 'Ok'
            });
            return;
        }
        if ($scope.orderItem.sizeId === undefined ||
            $scope.orderItem.sizeId === null ||
            $scope.orderItem.sizeId === "" ||
            $scope.orderItem.sizeId === 0) {
            Swal.fire({
                title: 'Warning',
                text: "Please select a Size",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#0979cf',
                cancelButtonColor: '#c9041e',
                confirmButtonText: 'Ok'
            });
            return;
        }
        $scope.orderItem.productId = $scope.dataId;
        $scope.orderItem.count = 1;
        $scope.orderItem.price = $scope.data.price;
        let existed = false;
        let existedIndex = -1;
        for (let i = 0; i < $scope.orderItemList.length; i++) {
            if ($scope.orderItemList[i].productId === $scope.orderItem.productId &&
                $scope.orderItemList[i].colorId === $scope.orderItem.colorId &&
                $scope.orderItemList[i].sizeId == $scope.orderItem.sizeId) {
                existed = true;
                existedIndex = i;
                break;
            }
        }

        if (!existed) {
            $scope.orderItemList.push({
                id: $scope.productId + "_" + $scope.orderItem.colorId + "_" + $scope.orderItem.sizeId,
                productId: $scope.orderItem.productId,
                count: $scope.orderItem.count,
                price: $scope.orderItem.price,
                //radix: 10 It considers the second city as the base. If you set base to 10, the input string will be interpreted as an integer in base 10 (decimal).
                sizeId: parseInt($scope.orderItem.sizeId, 10),
                colorId: $scope.orderItem.colorId,
                product: {
                    image: $scope.data.image,
                    title: $scope.data.title,
                },
                color: $scope.data.colorsList.filter(x => x.id === $scope.orderItem.colorId)[0],
                size: $scope.data.sizesList.filter(x => x.id == $scope.orderItem.sizeId)[0]

            });
            debugger;
            Swal.fire({
                title: $scope.data.title,
                text: "Added to basket",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#0979cf',
                cancelButtonColor: '#c9041e',
                confirmButtonText: 'Ok'
            });
        } else {
            $scope.orderItemList[existedIndex].count++;
            Swal.fire({
                title: $scope.data.title,
                text: "Added to basket (" + $scope.orderItemList[existedIndex].count + ")",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#0979cf',
                cancelButtonColor: '#c9041e',
                confirmButtonText: 'Ok'
            });
        }

        //$cookies.put("basket", $scope.orderItemList);
        //OrderItemList is object then use JSON.stringify(objects);
        //Problem: cookies (Path) is /product, For this reason, we have access to the basket cookie only in the addresses where /product is the first;
        //To solve this problem, we use {path: '/'}.
        $cookies.put("basket", JSON.stringify($scope.orderItemList), {path: '/'});

    }

    $scope.changeColor = (color) => {
        $scope.orderItem.colorId = color.id;
    }

    $scope.loadOrderItemList = () => {
        if ($cookies.get("basket") === undefined ||
            $cookies.get("basket") === null) {
            $scope.orderItemList = [];
            return;
        }
        // $scope.orderItemList = $cookies.get("basket");
        //Converts the json string in the cookies into an object.
        $scope.orderItemList = JSON.parse($cookies.get("basket"));
    }

    $scope.loadOrderItemList();

});