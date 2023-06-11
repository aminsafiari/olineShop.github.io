app.controller("paymentCtrl", function ($scope, $http, mainApiHandler, $rootScope, $cookies) {

        //for make active nav Title.
        $rootScope.page = "Payment";

        //get data from cookies.
        $scope.dataList = [];
        $scope.totalPrice = 0;
        $scope.totalCount = 0;
        $scope.data = {};
        $scope.paymentType = "NextPay";
        $scope.userLoggedIn = false;

        //get data for fill initial page.
        //fill from cookies.
        $scope.fillDataList = () => {
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

        $scope.gotoPayment = () => {
            let orderItems = [];
            for (let i = 0; i < $scope.dataList.length; i++) {
                let item = $scope.dataList[i];
                orderItems.push({
                    productId: item.productId,
                    colorId: item.colorId,
                    sizeId: item.sizeId,
                    count: item.count
                });
            }
            if (orderItems.length === 0) {
                swal.fire("Your basket is empty!");
                return;
            }
            let paymentVM = {
                customer: $scope.data,
                orderItemVMS: orderItems,
                paymentType: $scope.paymentType,
                customerId: $scope.data.customer.id
            }
            mainApiHandler.callPost('payment/', paymentVM, (response) => {
                debugger;
                //when any think ok.
                let href = response.dataList[0].location;
                location.href = href;
                // location.href = response.dataList[0].location;
            }, (err) => {

            });
        }

        $scope.init = () => {
            debugger;
            let token = $cookies.get("userToken");
            if (token != undefined && token != null) {
                $scope.getUserInfo(token);
            }
        }

        $scope.getUserInfo = (token) => {
            debugger;
            let request = {
                url: '/api/user/getUserInfo',
                method: 'GET',
                headers: {
                    "Authorization": "Bearer " + token
                }
            }
            $http(request).then((response) => {
                //when this work that you have Response.
                debugger;
                if (response != null && response.data != null) {
                    $scope.userLoggedIn = true;
                    $scope.data = response.data.dataList[0];
                    $scope.data.mobile = $scope.data.customer.mobile;
                    $scope.data.address = $scope.data.customer.address;
                    $scope.data.tel = $scope.data.customer.tel;
                    $scope.data.email = $scope.data.customer.email;
                    $scope.data.postalCode = $scope.data.customer.postalCode;
                }
            });
        }

        $scope.init();

        $scope.fillDataList();

    }
);