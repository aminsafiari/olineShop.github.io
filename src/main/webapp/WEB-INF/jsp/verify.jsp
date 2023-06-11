<%@ page import="amin.shop.app.entities.orders.Transactions" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | About</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/verifyController.js"></script>
    <%
        Transactions transaction = (Transactions) request.getAttribute("transaction");
    %>

</head>
<body ng-app="onlineShoppApp" ng-controller="verifyCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<div class="container-fluid py-4" ng-controller="contentCtrl">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">

            <div class="card text-center">
                <div class="card-header">
                    Payment Verify
                </div>
                <div class="card-body">
                    <p class="card-text">
                        Amount : <%=transaction.getAmount()%>
                        <br>
                        Status : <%if (transaction.getCode() == -1) {%>
                        <span class="badge bg-success">Successfully</span>
                        <br>
                        <strong class="mt-4">Reference Id : <%=transaction.getShaparak_Ref_Id()%>
                        </strong>
                        <% } else {%>
                        <span class="badge bg-danger">Failed</span>
                        <% } %>
                    </p>
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