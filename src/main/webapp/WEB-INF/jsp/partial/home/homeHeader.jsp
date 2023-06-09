<meta charset="UTF-8">

<link rel="shortcut icon" href="/images/logo.png"/>
<%--  BootStrap  --%>
<link rel="stylesheet" href="/libs/bootstrap-5.2.2-dist/css/bootstrap.min.css">
<script src="/libs/bootstrap-5.2.2-dist/js/bootstrap.bundle.min.js"></script>
<%--  Jquery  --%>
<%-- in parent header to home.jsp for run animation component carousel-lazy-loading-square, use ( libs/jquery-3.5.1.min.js ), beacuase dont need to (.slim.min) --%>
<%--<script src="libs/jquery-3.5.1.slim.min.js"></script>--%>
<script src="/libs/jquery-3.5.1.min.js"></script>
<%-- Font Awesome 6.2.1 --%>
<link rel="stylesheet" href="/libs/fontawesome-free-6.2.1-web/css/all.min.css">
<%--  angularJs  --%>
<script src="/libs/angular.min.js"></script>
<%-- angular cookies --%>
<script src="/libs/angular-cookies.js"></script>
<%-- angular-sanitize --%>
<%-- when use < ng-bind-html > make a unsafe error, use this lib for safe binding.  --%>
<%-- and ypu need defin ( ngSanitize ) in main controller for work. --%>
<script src="/libs/angular-sanitize.js"></script>
<%--  for confirm  --%>
<script src="/libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="/libs/sweetalert2/dist/sweetalert2.min.css">
<%--  mainApp  --%>
<script src="/scripts/mainApp.js"></script>
<script src="/scripts/services/mainApiHandler.js"></script>
<link href="/styles/main.css" rel="stylesheet">
<%--  controllers  --%>
<script src="/scripts/controllers/main/navController.js"></script>
<script src="/scripts/controllers/main/sliderController.js"></script>
<%-- footer content --%>
<script src="/scripts/controllers/main/contentController.js"></script>
<%-- Remove html tags --%>
<script src="/scripts/filters/removeHtmlTagsFilter.js"></script>