<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Products</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/productsController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="productsCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>
<%-- Show Categories --%>
<div class="container-fluid pt-4 bg-gradient">
    <div class="row">
        <div class="col py-2" ng-repeat="cat in categoryList">
            <div class="col-lg-2">
                <div class="d-flex">
                    <a href="products/{{cat.id}}" class="product-cat-item">
                        <div class="product-cat-image">
                            <img width="100%" src="/api/utils/upload/files/{{cat.image}}">
                        </div>
                        <div class="product-cat-title">{{cat.title}}</div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<%-- slide --%>
<div class="py-4">
    <img src="images/sliders/jeans.jpg" width="100%">
</div>

<%-- Products package --%>
<div class="container-fluid my-5">
    <div class="row">
        <%-- product list --%>
        <div class="col-md-3 pb-5 text-center">
            <div class="list-group mt-5 pt-5 text-truncate">
                <a href="" class="list-group-item list-group-item-action" ng-click="changeFilter('popular')"
                   ng-class="{'active' : selectedFilter === 'popular'}">Popular</a>
                <a href="" class="list-group-item list-group-item-action" ng-click="changeFilter('new')"
                   ng-class="{'active' : selectedFilter === 'new'}">New</a>
                <a href="" class="list-group-item list-group-item-action" ng-click="changeFilter('cheapest')"
                   ng-class="{'active' : selectedFilter === 'cheapest'}">Cheapest</a>
                <a href="" class="list-group-item list-group-item-action" ng-click="changeFilter('expensive')"
                   ng-class="{'active' : selectedFilter === 'expensive'}">Expensive</a>
            </div>
        </div>

        <%-- Show products --%>
        <div class="col pt-1">

            <h1 class="text-center fw-bold">{{selectedTitle}}</h1>
            <br>
            <div class="container mt-2 m-b-20">

                <div class="row">
                    <div class="col-md-4 m-b-20" ng-repeat="product in productsList">
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
                                   class="btn btn-sm text-light bg-gradient bg-primary float-end">Add
                                    to basket</a>
                            </div>
                        </div>
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