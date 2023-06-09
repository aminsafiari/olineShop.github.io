<!DOCTYPE html>
<html lang="en">
<head>

    <title>Online Shop App | Basket</title>
    <%--  for clean code we transformed the links and scripts path  --%>
    <jsp:include page="partial/home/homeHeader.jsp"></jsp:include>
    <script src="scripts/controllers/main/basketController.js"></script>

</head>
<body ng-app="onlineShoppApp" ng-controller="basketCtrl">

<%-- for clean code, transformed navigation code to path:<< partial/homeNav.jsp >> and include this where. --%>
<jsp:include page="partial/home/homeNav.jsp"></jsp:include>

<%-- Cards --%>
<div class="container-fluid py-4 min-height-500">
    <div class="row min-height-500">
        <div class="col-1"></div>
        <div class="col mt-5">

            <div class="card">
                <div class="card-header">
                    Shopping basket
                </div>
                <div class="card-body">

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Item</th>
                            <th scope="col">color</th>
                            <th scope="col">Size</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%-- TODO: (Practise) Add increase and decrease to the project. --%>
                        <tr ng-show="dataList.length == 0">
                            <td colspan="7" class="text-center">
                                <h3 class="p-2  fw-bold">Your basket is empty!</h3>
                            </td>
                        </tr>
                        <tr ng-repeat="data in dataList">
                            <th scope="row">{{ $index + 1 }}</th>
                            <td>
                                <a class="text-decoration-none" title="show Details" target="_blank"
                                   href="/product/{{data.productId}}">
                                    <img width="60" src="/api/utils/upload/files/{{data.product.image}}">
                                    <span class="ps-2">{{data.product.title}}</span>
                                </a>
                            </td>
                            <td>
                                <div class="pt-2">
                                    <div class="color-entity-box rounded-circle"
                                         style="background:{{data.color.value}}"></div>
                                    <span class="color-title">{{data.color.name}}</span>
                                </div>
                            </td>
                            <td>{{data.size.title}}</td>
                            <td>$ {{data.price}}</td>
                            <td>{{data.count}}</td>
                            <td>
                                <a ng-click="removeItem(data)" class="color-EyeOfNewt cursor-pointer"><i
                                        class="fa-solid fa-times"></i></a>
                            </td>
                        </tr>
                        </tbody>
                        <tfooter ng-show="dataList.length > 0" class="table-footer">
                            <tr>
                                <td colspan="3">#</td>
                                <td>Total</td>
                                <td>
                                    <%-- TODO: Discount implementation and vehicle patment,need to change manyPagecode,(part20 1:44). --%>
                                    <strong>$ {{totalPrice}}</strong>
                                </td>
                                <td>
                                    <strong>{{totalCount}}</strong>
                                </td>
                                <td></td>
                            </tr>
                        </tfooter>
                    </table>

                    <a ng-show="dataList.length > 0" href="/payment" class="btn btn-md btn-success m-t-35"><i
                            class="fa-solid fa-credit-card"></i>
                        Proceed to
                        payment</a>
                    <a href="/products" class="btn btn-md text-black btn-outline-info ms-1 m-t-35"><i
                            class="fa-solid fa-shopping-basket"></i> Continue Shopping</a>

                </div>
            </div>

        </div>
        <div class="col-1"></div>
    </div>
</div>

<%--TODO : Show customer item orders--%>

<%-- Footer --%>
<jsp:include page="partial/home/homeFooter.jsp"></jsp:include>

</body>
</html>