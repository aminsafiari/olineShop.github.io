app.controller("loginCtrl", function ($scope, apiHandler, $cookies) {
    $scope.user = {};

    $scope.doLogin = () => {
        //if you need debug any where in javascript, write << debugger; >>; action such as breakpoint.
        apiHandler.callPost("user/login",
            $scope.user,
            (response) => {
                //set token after login. we need use lib angular.cookie.js;
                let token = response.dataList[0].token;
                if (token == null || token == "") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Invalid Token!!',
                    });
                    alert("invalid token!!")
                }
                $cookies.put("userToken", token);
                location.href = "/panel";
            }, (error) => {

            });
    }
});