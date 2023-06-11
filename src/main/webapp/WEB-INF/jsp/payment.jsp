<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Payment</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/paymentController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="paymentCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%-- Cards --%>
<div class="container-fluid py-4">
    <div class="row min-height-500">
        <div class="col-1"></div>
        <div class="col-md-5 mt-5">

            <div class="card">
                <div class="card-header">
                    Payment Information
                </div>
                <div class="card-body">

                    <div class="row">
                        <div class="col">

                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Item</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-show="dataList.length == 0">
                                    <td colspan="6" class="text-center">
                                        <h3 class="p-2  fw-bold">Your basket is empty!</h3>
                                    </td>
                                </tr>
                                <tr ng-repeat="data in dataList">
                                    <td>
                                        <a class="text-decoration-none" title="show Details" target="_blank"
                                           href="/product/{{data.productId}}">
                                            <img width="60" src="/api/utils/upload/files/{{data.product.image}}">
                                            <span class="ps-2">{{data.product.title}}</span>
                                        </a>
                                    </td>
                                    <td>$ {{data.price}}</td>
                                    <td>{{data.count}}</td>
                                </tr>
                                </tbody>
                                <tfooter ng-show="dataList.length > 0" class="table-footer">
                                    <tr>
                                        <%--                                        <td colspan="3">#</td>--%>
                                        <td>Total</td>
                                        <td>
                                            <strong>$ {{totalPrice}}</strong>
                                        </td>
                                        <td>
                                            <strong>{{totalCount}}</strong>
                                        </td>
                                    </tr>
                                </tfooter>
                            </table>

                            <div class="mt-5">
                                <label for="PaymentType">Payment Type</label>
                                <div class="from-group mt-2" id="PaymentType">
                                    <select class="form-select" ng-model="paymentType">
                                        <option value="NextPay">NextPay</option>
                                    </select>
                                </div>
                            </div>

                            <a ng-click="gotoPayment()" class="btn btn-md btn-success m-t-35"><i
                                    class="fa-solid fa-credit-card"></i>
                                Proceed to
                                payment</a>

                        </div>
                    </div>

                </div>
            </div>

        </div>
        <div class="col-md-5 mt-5">

            <div class="card">
                <div class="card-header">
                    User Information
                </div>
                <div class="card-body">

                    <div class="row">
                        <div class="col">
                            <div class="row">
                                <!-- for sizes login form need to write div col-3 -->
                                <div class="col">

                                    <div class="row">

                                        <div class="col col-lg form-floating mb-3">
                                            <input type="text" placeholder="FirstName" class="form-control"
                                                   id="firstName"
                                                   ng-model="data.firstName">
                                            <label for="firstName">&nbsp;&nbsp;FirstName</label>
                                        </div>
                                        <div class="col col-lg form-floating mb-3">
                                            <input type="text" placeholder="LastName" class="form-control"
                                                   id="lastName"
                                                   ng-model="data.lastName">
                                            <label for="lastName">&nbsp;&nbsp;LastName</label>
                                        </div>

                                    </div>
                                    <div class="row">

                                        <div class="col col-lg form-floating mb-3">
                                            <input type="text" class="form-control"
                                                   id="username"
                                                   ng-model="data.username" placeholder="username">
                                            <label for="username"
                                                   class="col-form-label">&nbsp;&nbsp;Username</label>
                                        </div>
                                        <div class="col-lg form-floating mb-3">
                                            <input type="password" class="form-control" id="password"
                                                   ng-model="data.password" placeholder="Password">
                                            <label for="password"
                                                   class="col-form-label">&nbsp;&nbsp;Password</label>
                                        </div>

                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" placeholder="Email" class="form-control" id="email"
                                               ng-model="data.email">
                                        <label for="email">Email</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" placeholder="Mobile" class="form-control" id="mobile"
                                               ng-model="data.mobile">
                                        <label for="mobile">Mobile</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="tel" placeholder="Tel" class="form-control" id="tel"
                                               ng-model="data.tel">
                                        <label for="tel">Tel</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <textarea ng-model="data.address" placeholder="Address" class="form-control"
                                                  id="address" style="min-height: 6pc;"></textarea>
                                        <label for="address">Address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" placeholder="PostalCode" class="form-control" id="postal"
                                               ng-model="data.postalCode">
                                        <label for="postal">Postal</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

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