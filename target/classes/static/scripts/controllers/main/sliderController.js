app.controller("sliderCtrl", function ($scope, mainApiHandler) {

    //array object from Slider list in database.
    $scope.sliderList = [];

    $scope.getSliderData = () => {
        mainApiHandler.callGet("slider/", (response) => {
            $scope.sliderList = response.dataList;
            setTimeout(initSlider, 10);

        });
    }

    function initSlider() {
        $('.top-slider').square1({caption: 'none', theme: 'light'});
    }


    $scope.getSliderData();

});