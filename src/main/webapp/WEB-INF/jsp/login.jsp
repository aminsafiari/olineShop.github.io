<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online Shop App | Login</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/login/loginHeader.jsp"></jsp:include>

</head>
<body ng-app="onlineShoppApp">
<%--<jsp:include page="partial/home/homeNav.jsp"></jsp:include>--%>

<div class="container-fluid" ng-controller="loginCtrl">
    <div class="row">
        <div class="col-3"></div>
        <%-- for sizes login form need to write div col-3 --%>
        <div class="col login-box-holder">
            <h3>Login to panel</h3>
            <form>
                <div class="mb-3">
                    <label for="username" class="form-label">Email address</label>
                    <input type="text" class="form-control" id="username" ng-model="user.username">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" ng-model="user.password">
                </div>
                <button type="submit" class="btn btn-primary" ng-click="doLogin()">Login</button>
            </form>
        </div>
        <div class="col-3"></div>
    </div>
</div>

</body>
</html>