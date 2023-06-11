app.controller('productAddCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.category = $rootScope.Category;
    $scope.colors = [];
    $scope.sizes = [];
    //newFeature is object.
    $scope.newFeature = {};
    //features is array and for save featureId.
    $scope.data.features = [];
    //featureList is array and for save feature-object for show.
    $scope.featureList = [];
    $scope.addData = () => {
        $scope.data.image = $rootScope.uploadedFile;
        $scope.data.categoryId = $scope.category.id;
        //check in user-interface Layer (client side).
        if ($scope.data.title === undefined || $scope.data.title == null || $scope.data.title === "") {
            Swal.fire('Please enter title!!');
            return;
        }
        if ($scope.data.price === undefined || $scope.data.price == null || $scope.data.price === "") {
            Swal.fire('Please enter price!!');
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire('Please set enable!!');
            return;
        }
        if ($scope.data.exists === undefined || $scope.data.exists == null) {
            Swal.fire('Please set exists!!');
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === "") {
            Swal.fire('Please upload an image!!');
            return;
        }
        apiHandler.callPost("product/", $scope.data, (response) => {
            $scope.changeMenuWithCategory("product-list");
        }, (error) => {
        }, true);
    };

    $scope.changeMenuWithCategory = (template) => {
        $rootScope.Category = $scope.category;
        $scope.changeMenu(template);
    }

    /**
     *get all colors in list
     */
    $scope.getColors = () => {
        //api->(color/) = getAll.
        apiHandler.callGet("color/", (response) => {
            $scope.colors = response.dataList;
        }, (error) => {
        }, true)
    };


    /**
     *get all sizes in list
     */
    $scope.getSizes = () => {
        //api->(size/) = getAll.
        apiHandler.callGet("size/", (response) => {
            $scope.sizes = response.dataList;
        }, (error) => {
        }, true)
    };
    /**
     * add new feature.
     */
    $scope.addFeature = () => {
        apiHandler.callPost("feature/", $scope.newFeature, (response) => {
            //push object in data.features.
            //i have feature list ,then add feature give me feature, for insert data.
            $scope.data.features.push(response.dataList[0].id);
            //for show data in user interface.
            $scope.featureList.push(response.dataList[0]);
            //After adding the feature, our work is finished and we empty the newFeature.
            $scope.newFeature = {};
        }, (error) => {
        }, true)
    };

    /**
     * delete feature by id.
     */
    $scope.deleteFeature = (featureId) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#0979cf',
            cancelButtonColor: '#c90449',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                if ($scope.dataId === undefined || $scope.dataId === null) {
                    $scope.dataId = -1;
                }
                apiHandler.callDelete("feature/" + featureId + "/?productId=" + $scope.dataId, (response) => {
                    //delete id in data.features.
                    for (let i = 0; i < $scope.data.features.length; i++) {
                        //for remove once feature by id feature.
                        if ($scope.data.features[i] === featureId) {
                            $scope.data.features.splice(i, 1);
                            break;
                        }
                    }
                    //delete Object in featureList.
                    for (let i = 0; i < $scope.featureList.length; i++) {
                        //for remove once feature by id feature.
                        if ($scope.featureList[i].id === featureId) {
                            $scope.featureList.splice(i, 1);
                            break;
                        }
                    }
                }, (error) => {
                }, true)
            }
        })
    };

    $scope.getColors();
    $scope.getSizes();


});
