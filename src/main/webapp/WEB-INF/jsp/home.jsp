<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Home</title>

    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <%--  becuase slider for this, write this.  --%>
    <%--    <link rel="stylesheet" href="libs/carousel-lazy-loading-square/square1.min.css"/>--%>
    <%--    <link rel="stylesheet" href="libs/carousel-lazy-loading-square/square2.css">--%>
    <%--    <script src="libs/carousel-lazy-loading-square/square1.min.js"></script>--%>
    <script src="scripts/controllers/main/homeController.js"></script>
    <script src="scripts/controllers/main/sliderController.js"></script>
    <script src="scripts/controllers/main/productController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="homeCtrl">

<jsp:include page="partial/home/homeNav.jsp"></jsp:include>
<%-- Slider-Top --%>
<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<div ng-controller="sliderCtrl">

    <div class="row pb-5">
        <div class="col">

            <div id="carouselExampleDark" class="carousel carousel-light slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button ng-repeat="slider in sliderList" type="button" data-bs-target="#carouselExampleDark"
                            data-bs-slide-to="{{$index}}" ng-class="{active: $index == 0}"
                            aria-label="Slider {{$index+1}}"></button>
                </div>
                <div class="carousel-inner">
                    <div ng-repeat="slider in sliderList" class="carousel-item" ng-class="{active: $index == 0}"
                         data-bs-interval="2500">
                        <img ng-src="/api/utils/upload/files/{{slider.image}}" class="d-block w-100"
                             alt="{{slider.title}}">
                        <div class="carousel-caption d-none d-md-block text-start">
                            <a href="/products" class="text-decoration-none text-dark">
                                <h5 class="">{{slider.title}}</h5>
                                <h1 class="text-dark text-muted">{{slider.description}}</h1>
                            </a>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>


        </div>
    </div>

    <%-- use lib caruosel-lazy --%>
    <%--
        <div class="slideshow top-slider position-relative">
            <div ng-repeat="slider in sliderList">
                <a href="{{slider.link}}">
                    <img style="background-size: auto;" src="/api/utils/upload/files/{{slider.image}}"
                         alt="{{slider.title}}">
                    <div class="text_content">
                        <h3>{{slider.title}}</h3>
                        <h5>{{slider.description}}</h5>
                    </div>
                </a>
            </div>
        </div>
        --%>


    <%-- Products --%>
    <div ng-controller="productCtrl">
        <h1 class="text-center fw-bold">New Product</h1>
        <br>
        <div class="container mt-2 m-b-20">

            <div class="row">
                <div class="col-md-4 m-b-20" ng-repeat="product in newProductList">
                    <div class="card">
                            <img width="200" src="/api/utils/upload/files/{{product.image}}" class="card-img-top"
                                 alt="{{product.title}}">
                        <div class="card-body">
                            <h4 class="card-title mb-4 text-center d-block text-truncate">{{product.title}}</h4>
                            <div class="card-text">
                                <ul>
                                    <li ng-repeat="feature in product.featuresDataList | limitTo:5">
                                        <small class="d-block text-truncate"> {{feature.key}} :
                                            {{feature.value}} </small>
                                    </li>
                                </ul>
                                <%-- <p ng-repeat="feature in product.featuresDataList">
                                     <small class="text-secondary"> {{feature.key}} : {{feature.value}} </small>
                                 </p>--%>
                            </div>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">{{product.addDateStr}}</small>
                            <a href="product/{{product.id}}"
                               class="btn btn-sm text-light bg-primary float-end bg-gradient">Add
                                to
                                basket</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <%-- Parallax-Center --%>
        <div class="parallax-cover"></div>

        <%-- Popular Products --%>
        <h1 class="text-center fw-bold m-t-35">Popular Products</h1>
        <br>
        <div class="container mt-3 mb-5">

            <div class="row">
                <div class="col-md-4 m-b-20" ng-repeat="product in popularProductList">
                    <div class="card">
                        <img width="200" src="/api/utils/upload/files/{{product.image}}" class="card-img-top"
                             alt="{{product.title}}">
                        <div class="card-body">
                            <h4 class="card-title mb-4 text-center d-block text-truncate">{{product.title}}</h4>
                            <div class="card-text">
                                <ul>
                                    <li ng-repeat="feature in product.featuresDataList">
                                        <small class="d-block text-truncate"> {{feature.key}} :
                                            {{feature.value}} </small>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">{{product.addDateStr}}</small>
                            <a href="product/{{product.id}}"
                               class="btn btn-sm text-light bg-primary float-end bg-gradient">Add
                                to
                                basket</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <%-- Footer --%>
    <jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>