app.controller("panelCtrl", function ($scope, apiHandler, $cookies, $rootScope) {

    $scope.template = "views/dashboard.html";
    $scope.templateName = "dashboard";
    $scope.templateGroup = "dashboard";

    $scope.checkAccess = () => {
        let token = $cookies.get("userToken");
        if (token === undefined || token === null || token === "") {
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();
    };

    $scope.getUserInfo = () => {
        apiHandler.callGet("user/getUserInfo", (response) => {
            $rootScope.userInfo = response.dataList[0];
            $scope.user = $rootScope.userInfo;
        }, (error) => {

        }, true)
    };


    $scope.changeMenu = (templateName) => {
        $scope.templateName = templateName;
        $scope.template = $scope.getMenuPrefix(templateName);
        $scope.templateGroup = $scope.getMenuGroup(templateName);
    };

    $scope.getMenuPrefix = (templateName) => {
        switch (templateName) {
            case "dashboard":
                return "views/" + templateName + ".html";
            case "nav-list":
            case "nav-add":
            case "nav-edit":
                return "views/site/nav/" + templateName + ".html";
            case "content-list":
            case "content-add":
            case "content-edit":
                return "views/site/content/" + templateName + ".html";
            case "slider-list":
            case "slider-add":
            case "slider-edit":
                return "views/site/slider/" + templateName + ".html";
            case "blog-list":
            case "blog-add":
            case "blog-edit":
                return "views/site/blog/" + templateName + ".html";
            case "user-list":
            case "user-add":
            case "user-edit":
                return "views/people/users/" + templateName + ".html";
            case "category-list":
            case "category-add":
            case "category-edit":
                return "views/products/category/" + templateName + ".html";
            case "color-list":
            case "color-add":
            case "color-edit":
                return "views/products/color/" + templateName + ".html";
            case "size-list":
            case "size-add":
            case "size-edit":
                return "views/products/size/" + templateName + ".html";
            case "product-list":
            case "product-add":
            case "product-edit":
                return "views/products/product/" + templateName + ".html";
            case "customer-dashboard":
            case "invoice-detail":
            case "customer-edit":
                return "views/customer-dashboard/" + templateName + ".html";
            case "customer-list":
                return "views/people/customers/" + templateName + ".html"
            case "uploader":
                return "views/util/" + templateName + ".html";
            default :
                return "views/dashboard.html";
        }
    };

    //for activate menu title.
    $scope.getMenuGroup = (templateName) => {
        switch (templateName) {
            case "dashboard":
                return "dashboard";
            case "nav-list":
            case "nav-edit":
            case "nav-insert":
                return "nav";
            case "content-list":
            case "content-add":
            case "content-edit":
                return "content";
            case "slider-list":
            case "slider-add":
            case "slider-edit":
                return "slider";
            case "blog-list":
            case "blog-add":
            case "blog-edit":
                return "blog";
            case "user-list":
            case "user-add":
            case "user-edit":
                return "user";
            case "color-list":
            case "color-add":
            case "color-edit":
            case "size-list":
            case "size-add":
            case "size-edit":
            case "product-list":
            case "product-add":
            case "product-edit":
            case "category-list":
            case "category-add":
            case "category-edit":
                return "product";
            case"customer-dashboard":
            case"invoice-detail":
                return "customer-dashboard";
            case"customer-edit":
                return "customer-edit";
            case"customer-list":
                return "customer";
            case"uploader":
                return "uploader";
            default :
                return "dashboard";
        }
    };

    $scope.logOut = () => {
        $cookies.remove("userToken");
        location.href = "/login";
    }

    $scope.init = (cid) => {
        debugger;
        $rootScope.currentCustomerId = cid;
        if (cid == undefined || cid == null || cid == 0) {
            $scope.changeMenu("dashboard");
        } else {
            $scope.changeMenu("customer-dashboard");
        }
    }

    $scope.checkAccess();
});