<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Blog</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/blogController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="blogCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%-- Slider --%>
<div>
    <img src="images/sliders/blog.jpg" width="100%">
</div>

<%-- Cards --%>
<div class="container-fluid my-4">
    <div class="card my-3" ng-repeat="blog in blogList">
        <div class="row g-0">
            <%-- slide --%>
            <div class="col-xxl-1 col-md-2 align-self-center">
                <img src="/api/utils/upload/files/{{blog.image}}" width="130"
                     class="img-fluid rounded-start justify-content-center"
                     alt="slide">
            </div>
            <%-- cards --%>
            <div class="col">
                <div class="card-body">
                    <h5 class="card-title text-truncate">{{blog.title}}</h5>
                    <p class="card-text text-truncate">{{blog.subtitle}}</p>
                    <p class="card-text"><small class="text-muted">{{blog.publishDateStr}}</small>
                        <a href="blog/{{blog.id}}" class="btn btn-primary btn-sm float-end">Read more...</a></p>
                </div>
            </div>
        </div>
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