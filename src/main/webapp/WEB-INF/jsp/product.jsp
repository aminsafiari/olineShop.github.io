<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | About</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="/scripts/controllers/main/productInfoController.js"></script>

    <%-- Read dataId from attribute --%>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>

</head>
<body ng-app="onlineShoppApp" ng-controller="productInfoCtrl" ng-init="init(<%=dataId%>)">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%--  --%>
<%-- ng-init for send dataId(give from attribute) to init method in productInfoCtrl up to requste and give product data --%>
<div class="container-fluid my-5">
    <div class="row">
        <div class="col-lg-5 col-md-12 mb-5 text-center">
            <img src="/api/utils/upload/files/{{data.image}}" class="rounded-5" width="75%">
        </div>

        <div class="col">
            <div class="card">
                <div class="card-header text-center text-muted fw-bold h3">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{data.title}}
                    <a href="/products/{{data.categoryId}}"
                       class="btn btn-lg  btn-outline-dark text-danger border-secondary float-end bg-gradient">Return</a>
                </div>
                <div class="card-body text-start">

                    <%-- show colors and sizes --%>
                    <div class="col-3 form-group my-4">
                        <label class="pb-3 fw-bold" for="colors">Color</label>
                        <%--                        <div id="colors" class="d-flex" ng-repeat="item in data.colorsList">--%>
                        <div id="colors" ng-repeat="item in data.colorsList">
                            <div class="color-entity-box rounded-circle"
                                 style="background:{{item.value}}"></div>
                            <div class="color-title">
                                <input name="color" type="radio" id="color_{{item.id}}"
                                       ng-value="{{item.id}}"
                                       ng-model="orderItem.colorId">
                                <label class="label_hover" for="color_{{item.id}}">{{item.name}}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-2 form-group mb-3">
                        <label class="fw-bold pb-3" for="sizes">Size</label>
                        <select class="form-select" aria-label="Default select example" id="sizes"
                                ng-model="orderItem.sizeId">
                            <option selected ng-show="false"></option>
                            <option ng-repeat="item in data.sizesList" value="{{item.id}}">
                                {{item.title}}
                            </option>
                        </select>
                    </div>

                    <%-- botton Add to basket --%>
                    <div class="pb-3">
                        <a class="btn btn-outline-dark text-primary border-secondary mt-2"
                           ng-click="addToBasket()">+ Add To Basket</a>
                    </div>

                    <hr>

                    <%-- Show descroption --%>
                    <p class="card-text border-bottom border-success" ng-bind-html="data.description"></p>
                    <ul class="text-start mt-0 m-b-20">
                        <div class="text-start text-success fw-bold h4 pb-3 pe-4">
                            Product Features :
                        </div>
                        <li class="ms-5" ng-repeat="feature in data.featuresDataList">
                            {{feature.key}} : {{feature.value}}
                        </li>
                    </ul>

                </div>
                <div class="card-footer text-center text-muted">
                    <i class="fa-solid fa-calendar"></i>
                    <span>{{data.addDateStr}}</span>
                    <i class="fa-solid fa-eye ps-3"></i>
                    <span>{{data.visitCount}}</span>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- Footer --%>
<jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>