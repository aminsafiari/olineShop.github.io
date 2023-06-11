<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | About</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/aboutController.js"></script>
    <script src="scripts/controllers/main/contentController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="aboutCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>
<%-- slide --%>
<div>
    <div id="carouselExample" class="carousel slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/sliders/about.jpg" class="w-100 h-50" alt="...">
            </div>
        </div>
    </div>
</div>

<%-- Cards --%>
<div class="container-fluid py-4" ng-controller="contentCtrl">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">

            <div class="card text-center">
                <div class="card-header">
                    About Us
                </div>
                <div class="card-body">
                    <p class="card-text" ng-bind-html="getContent('about')"></p>
                    <a href="#" class="btn btn-outline-dark text-primary border-info">Go Shopping</a>
                </div>
                <div class="card-footer text-muted pt-3 fw-bold" ng-bind-html="getContent('about_slogan')"></div>
            </div>

        </div>
        <div class="col-1"></div>
    </div>
</div>

<%-- Footer --%>
<jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>