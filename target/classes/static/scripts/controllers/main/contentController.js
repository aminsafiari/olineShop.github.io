app.controller("contentCtrl", function ($scope, mainApiHandler) {

    //array object from content list in database.
    $scope.contentList = [];

    //get all data from content.
    $scope.getContentData = () => {
        mainApiHandler.callGet("content/getAllData/", (response) => {
            $scope.contentList = response.dataList;
        });
    }

    $scope.getContent = (key) => {
        const content = $scope.contentList.find(content => content.key === key);
        return content ? content.value : undefined;
    }

    $scope.getContentData();

});