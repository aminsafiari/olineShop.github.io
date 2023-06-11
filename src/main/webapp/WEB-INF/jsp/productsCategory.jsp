<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Blog</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="/scripts/controllers/main/productsCategoryController.js"></script>

    <%-- Read dataId from attribute --%>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>

</head>
<body ng-app="onlineShoppApp" ng-controller="productsCategoryCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%-- Cards --%>
<%-- ng-init for send dataId(give from attribute) to init method in blogInfoCtrl up to requste and give blog data --%>
<div class="container-fluid py-5" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">

            <div class="row mb-5">
                <h1 class="text-center fw-bold my-5 text-center">{{category.title}}
                    <a href="/products" class="btn btn-lg btn-outline-dark text-danger border-secondary float-start bg-gradient">Return</a>
                </h1>
            </div>

            <div class="container mt-3 mb-5">

                <div class="row">
                    <div class="col-md-4 m-b-20" ng-repeat="product in dataList">
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
                                <a href="../product/{{product.id}}"
                                   class="btn btn-sm text-light bg-primary float-end bg-gradient">Add to
                                    basket</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <div class="col-1"></div>
    </div>

    <%-- Pagination --%>
    <%-- TODO: when in blog page For example 7 and select read more... come in blogInfo page and return, see page 7.   --%>
    <div class="container-fluid mt-5">
        <nav class="row">
            <div class="col-3"></div>
            <div class="col text-center">
                <ul class="pagination pagination-sm cursor-pointer">
                    <li ng-repeat="i in range(pageCount) track by $index" class="page-item" aria-current="page">
                    <span class="page-link cursor-pointer" ng-click="changePage($index)">
                        {{ ($index + 1) }}
                    </span>
                    </li>
                </ul>
            </div>
            <div class="col-3"></div>
        </nav>
    </div>

</div>


<%-- Footer --%>
<jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>