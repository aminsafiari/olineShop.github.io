app.controller("contentCtrl", function ($scope, mainApiHandler) {

    //array object from content list in database.
    $scope.contentList = [];

    //get all data from content.
    $scope.getContentData = () => {
        mainApiHandler.callGet("content/getAllData/", (response) => {
            $scope.contentList = response.dataList;
        });
    }

    //I have an object content, come and compare it with the key that comes from the input, if there is, return it.
    /*$scope.getContent = (key) => {
        for (let i = 0; i < $scope.contentList.length; i++) {
            if ($scope.contentList[i].key === key) {
                return $scope.contentList[i].value;
            }
        }
    }*/
    $scope.getContent = (key) => {
        const content = $scope.contentList.find(content => content.key === key);
        return content ? content.value : undefined;
    }


    $scope.getContentData();

});