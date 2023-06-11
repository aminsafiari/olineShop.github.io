app.controller("blogInfoCtrl", function ($scope, mainApiHandler, $rootScope) {

    //For activate in list.
    $rootScope.page = "Blog";
    //For give blog id from server.
    $scope.dataId = 0;
    //For get data from response.
    $scope.data = {};

    $scope.getBlogInfo = () => {
        let url = "blog/info/" + $scope.dataId;
        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.dataList[0];
        });
    }

    //For give blog id in bolg.jsp then run getBlogInfo method.
    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getBlogInfo();
    }

});