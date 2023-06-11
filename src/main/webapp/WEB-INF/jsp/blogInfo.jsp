<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Blog</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="/scripts/controllers/main/blogInfoController.js"></script>

    <%-- Read dataId from attribute --%>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>

</head>
<body ng-app="onlineShoppApp" ng-controller="blogInfoCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%-- Cards --%>
<%-- ng-init for send dataId(give from attribute) to init method in blogInfoCtrl up to requste and give blog data --%>
<div class="container-fluid py-5" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">

            <div class="card text-center">
                <div class="card-header h1 fw-bold">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{data.title}}
                    <a href="/blog" class="btn btn-lg btn-outline-dark text-danger border-secondary float-end bg-gradient">Return</a>
                </div>
                <div class="card-body">
                    <h3 class="fw-bold">{{data.subtitle}}</h3>
                    <div class="py-3 border-bottom">
                        <img src="/api/utils/upload/files/{{data.image}}" class="rounded-3" width="15%">
                    </div>
                    <p class="card-text pt-3" ng-bind-html="data.description"></p>
                </div>
                <div class="card-footer text-muted">
                    <i class="fa-solid fa-calendar"></i>
                    <span class="pe-4">{{data.publishDateStr}}</span>

                    <i class="fa-solid fa-eye ps-1"></i>
                    <span>{{data.visitCount}}</span>
                </div>
            </div>

        </div>
        <div class="col-1"></div>
    </div>
</div>

<%-- Footer --%>
<jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>