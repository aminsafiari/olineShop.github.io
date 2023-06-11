app.controller("customerEditCtrl", function ($scope, apiHandler, $rootScope) {

    $rootScope.page = "Customer";
    $scope.data = {};

    $scope.getUserInfo = () => {
        apiHandler.callGet("user/getUserInfo", (response) => {
            debugger;
            $scope.data = response.dataList[0];
            $scope.data.mobile = $scope.data.customer.mobile;
            $scope.data.address = $scope.data.customer.address;
            $scope.data.tel = $scope.data.customer.tel;
            $scope.data.email = $scope.data.customer.email;
            $scope.data.postalCode = $scope.data.customer.postalCode;
        }, (error) => {

        }, true)
    };

    $scope.editData = () => {
        debugger;
        let customerVM = {
            id: $scope.data.customerId,
            firstName: $scope.data.firstName,
            lastName: $scope.data.lastName,
            mobile: $scope.data.mobile,
            tel: $scope.data.tel,
            address: $scope.data.address,
            postalCode: $scope.data.postalCode,
            email: $scope.data.email,
            username: $scope.data.username,
            password: $scope.data.password,
            userId: $scope.data.id
        }
        apiHandler.callPut('customer/updateInfo', customerVM, (response) => {
            debugger;
            swal.fire('your data has been updated');
            $scope.changeMenu('customer-dashboard');
        }, (error) => {

        }, true);
    }

    $scope.getUserInfo();


});