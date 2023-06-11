app.controller('productEditCtrl', function ($scope, apiHandler, $rootScope) {

    // Todo: edit size have a bug(not change size).

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    //dataId -> category (product, color,...).
    $scope.category = $rootScope.Category;
    $scope.colors = [];
    $scope.sizes = [];
    //newFeature is object.
    $scope.newFeature = {};
    //features is array and for save featureId.
    $scope.data.features = [];
    //featureList is array and for save feature-object for show.
    $scope.featureList = [];
    //define for onColorChange method.
    $scope.selectedColors = [];
    //define for onSizeChange method.
    $scope.selectedSizes = [];

    $scope.editData = () => {
        if ($rootScope.uploadedFile !== undefined && $rootScope.uploadedFile !== null
            && $rootScope.uploadedFile !== "") {
            $scope.data.image = $rootScope.uploadedFile;
        }
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

        //My code.
        $scope.data.colors = [];
        for (let i = 0; i < $scope.selectedColors.length; i++) {
            $scope.colors.forEach(x => {
                if (x.id === $scope.selectedColors[i])
                    $scope.data.colors.push(x.id);
            })
        }
        $scope.data.sizes = [];
        for (let i = 0; i < $scope.selectedSizes.length; i++) {
            $scope.sizes.forEach(x => {
                if (x.id === $scope.selectedSizes[i])
                    $scope.data.sizes.push(x.id);
            })
        }

        //when sending data to the server,(It is true that the data (Exist) sent to the server is called (exists), but the data sent from the user to the server must be called (exist), otherwise it will not work properly.)
        $scope.data.exist = Boolean($scope.data.exists);
        //In -$scope.data.features has an Int(id for the features) that throws an exception when sent to the server, so we define it like this -$scope.data.features from Feature-Object that this object is in -$scope.data.featuresDataList .
        // $scope.data.features = $scope.featureList;

        apiHandler.callPut("product/", $scope.data, (response) => {
            $scope.changeMenu("product-list");
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

    $scope.fillFeatures = () => {
        for (let i = 0; i < $scope.data.featuresDataList.length; i++) {
            $scope.featureList.push($scope.data.featuresDataList[i]);
        }
    };

    $scope.getData = () => {
        apiHandler.callGet("product/info/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
            //for onColorChange method.
            for (let i = 0; i < $scope.data.colors.length; i++) {
                $scope.selectedColors.push($scope.data.colors[i]);
            }
            //for onSizeChange method.
            for (let i = 0; i < $scope.data.sizes.length; i++) {
                $scope.selectedSizes.push($scope.data.sizes[i]);
            }

            $scope.fillFeatures();
        }, (error) => {
        }, true);
    };

    $scope.isSelected = (list, item) => {
        if (list === undefined) return false;
        //.some mean Even if it has an item return <true>.
        return list.some(x => x === item.id);
    };

    $scope.onColorChange = (color) => {
        for (let i = 0; i < $scope.selectedColors.length; i++) {
            if ($scope.selectedColors[i] === color.id) {
                $scope.selectedColors.splice(i, 1);
                return;
            }
        }
        $scope.selectedColors.push(color.id);
    }

    $scope.onSizeChange = (size) => {
        for (let i = 0; i < $scope.selectedSizes.length; i++) {
            if ($scope.selectedSizes[i] === size.id) {
                $scope.selectedSizes.splice(i, 1);
                return;
            }
        }
        $scope.selectedSizes.push(size.id);
    }

    $scope.getColors();
    $scope.getSizes();
    $scope.getData();


});