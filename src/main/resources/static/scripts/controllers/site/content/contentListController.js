app.controller('contentListCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };

    $scope.dataList = [];
    $scope.pageCount = 0;
    $scope.totalCount = 0;

    $scope.getDataList = () => {
        let url = "content/getAll?pageSize=" + $scope.query.pageSize +
            "&pageNumber=" + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.query.pageSize > 0)
                $scope.pageCount++;
        }, (error) => {

        }, true);
    };

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
    };

    $scope.range = (max) => {
        return new Array(max);
    };

    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu("content-edit");
    };

    //dont need delete.because if i want delete data, i delete part of site information.

    $scope.changeOrder = (id, direction) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to change order?!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#0979cf',
            cancelButtonColor: '#c90449',
            confirmButtonText: 'Yes, change it!'
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callPost("content/changeOrder/" + id + "/" + direction, null, (response) => {
                    Swal.fire(
                        'Changed!',
                        'Your data has been changed.',
                        'success'
                    );
                    $scope.getDataList();
                }, (error) => {

                }, true);
            }
        });
    }

    //i definition because information in content is may be long.
    $scope.smallSubStr = (content) => {
        //0 up to 100 mean, give for zero character up to 100 character.
        return content.substr(0, 100);
    }

    $scope.getDataList();

});