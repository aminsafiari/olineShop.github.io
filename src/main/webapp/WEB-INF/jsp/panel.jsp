<%@ page import="amin.shop.app.helper.uimodels.UserVM" %>
<%@ page import="amin.shop.app.enums.UserRole" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Login</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/panel/panelHeader.jsp"></jsp:include>
    <%
        UserVM user = (UserVM) request.getAttribute("user");
    %>

</head>
<body ng-app="onlineShoppApp">
<% if (user != null) { %>
<div class="container-fluid" ng-controller="panelCtrl" ng-init="init(<%=user.getCustomerId()%>)">
    <div class="row">
        <div class="col p-0">
            <div class="panel-header">
                <a ng-click="logOut()" class="btn btn-danger btn-sm">Logout</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-2 p-0">
            <div class="slide-nav">

                <div class="text-center p-3">
                    <img class="slide-nav-avatar" src="images/avatar.png" width="100"/>
                    <br>
                    <%-- this user handle client side--%>
                    <span>{{user.fullName}}</span>
                </div>

                <ul>
                    <li>
                        <a href="/">
                            <i class="fa fa-globe"></i>
                            <span>Website </span>
                        </a>
                    </li>
                    <%-- this user handle server side--%>
                    <%-- Because we did this from the server side, it is safe, and because we used (if), if we hit ng-show, it will not be shown to anyone. --%>
                    <% if (user.getRole() == UserRole.ADMIN) { %>
                    <li ng-click="changeMenu('dashboard')"
                        ng-class="{'slid-nav-active' : templateGroup == 'dashboard'}">
                        <a href="#">
                            <i class="fa fa-link"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('nav-list')" ng-class="{'slid-nav-active' : templateGroup == 'nav'}">
                        <a href="#">
                            <i class="fa fa-link"></i>
                            <span>Navigations</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('content-list')"
                        ng-class="{'slid-nav-active' : templateGroup == 'content'}">
                        <a href="#">
                            <i class="fa fa-file"></i>
                            <span>Content</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('slider-list')" ng-class="{'slid-nav-active' : templateGroup == 'slider'}">
                        <a href="#">
                            <i class="fa fa-photo-video"></i>
                            <span>Sliders</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('blog-list')" ng-class="{'slid-nav-active' : templateGroup == 'blog'}">
                        <a href="#">
                            <i class="fa fa-newspaper"></i>
                            <span>Blog</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('category-list')"
                        ng-class="{'slid-nav-active' : templateGroup == 'product'}">
                        <a href="#">
                            <i class="fa fa-cubes"></i>
                            <span>Products</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('user-list')" ng-class="{'slid-nav-active' : templateGroup == 'user'}">
                        <a href="#">
                            <i class="fa fa-users"></i>
                            <span>Users</span>
                        </a>
                    </li>
                    <%--Invoice and OrderItem Handel with Customers --%>
                    <li ng-click="changeMenu('customer-list')" ng-class="{'slid-nav-active' : templateGroup == 'customer'}">
                        <a href="#">
                            <i class="fa fa-shopping-bag"></i>
                            <span>Customer</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('uploader')" ng-class="{'slid-nav-active' : templateGroup == 'uploader'}">
                        <a href="#">
                            <i class="fa fa-photo-video"></i>
                            <span>File Manager</span>
                        </a>
                    </li>
                    <% } else { %>
                    <li ng-click="changeMenu('customer-dashboard')"
                        ng-class="{'slid-nav-active' : templateGroup == 'customer-dashboard'}">
                        <a href="#" ng-click="changeMenu('customer-dashboard')">
                            <i class="fa fa-link"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('customer-edit')"
                        ng-class="{'slid-nav-active' : templateGroup == 'customer-edit'}">
                        <a href="#" ng-click="changeMenu('customer-edit')">
                            <i class="fa fa-user"></i>
                            <span>Profile</span>
                        </a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <div class="col p-0">
            <div class="main-container" ng-include="template"></div>
        </div>
    </div>
</div>
<% } else { %>
<script>
    location.href = "/login";
</script>
<% } %>
</body>
</html>