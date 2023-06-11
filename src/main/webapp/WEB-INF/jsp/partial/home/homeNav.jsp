<%-- uses this code in << home.jsp >> --%>
<div class="" ng-controller="navCtrl">
    <br>
    <br>
    <br>
    <nav class="navbar navbar-expand-lg navbar-expand-lg bg-light fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">
                <img src="/images/logo.png" width="60">
            </a>
            <%--    (toggler)s => for hamberger menu for mobile    --%>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item" ng-repeat="nav in navList" ng-class="{'active':page == nav.title}">
                        <a class="nav-link" aria-current="page" href="{{nav.link}}">{{nav.title}}</a>
                    </li>
                </ul>
                <div class="d-flex" role="search">
                    <a href="/panel" class="btn btn-outline-success me-2"><i
                            class="fa-solid fa-user"></i></a>
                    <a href="/basket" class="btn btn-outline-primary me-2"><i
                            class="fa-solid fa-shopping-basket"></i></a>
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                           ng-model="searchKey">
                    <button class="btn btn-outline-success" type="submit" ng-click="productsSearch()">Search</button>
                </div>
            </div>
        </div>
    </nav>
</div>
