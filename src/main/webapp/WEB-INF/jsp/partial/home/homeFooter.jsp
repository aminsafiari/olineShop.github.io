<footer class="main-footer bg-gradient" ng-controller="contentCtrl">
    <div class="container">
        <div class="row">

            <%-- Left --%>
            <div class="col">

                <div>
                    <div class="footer-circle-item">
                        <i class="fa-solid fa-solid fa-location-dot"></i>
                    </div>
                    <div class="footer-circle-item-title" ng-bind-html="getContent('address')"></div>
                </div>
                <div class="pt-3">
                    <div class="footer-circle-item">
                        <i class="fa-solid fa-phone"></i>
                    </div>
                    <%-- The "tel:(...)" in this code is a customized link that can be used to specify a phone number within an HTML tag. When the user clicks on the link, depending on the web operating system in use, an operation can be performed that can be used to call the phone number, send a text message, or perform other related actions. --%>
                    <a href="tel:{{ getContent('tel') | removeHTMLTags }}"
                       class="footer-circle-item-title footer-content-balance fw-bold"
                       ng-bind-html="getContent('tel')"></a>
                </div>
                <div>
                    <div class="footer-circle-item">
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <%-- mailto: , such as under comment but for email --%>
                    <a href="mailto:{{ getContent('email') | removeHTMLTags }}"
                       class="footer-circle-item-title footer-content-balance"
                       ng-bind-html="getContent('email')"></a>
                </div>

            </div>

            <%-- Right --%>
            <div class="col">

                <h4 class="fw-bold pt-4">About the company</h4>
                <p class="footer-description pt-0" ng-bind-html="getContent('footer_about')"></p>
                <div class="pt-2">
                    <a href="{{ getContent('instagram_link') | removeHTMLTags}}" class="footer-social">
                        <img src="/images/socialMedia/instagram.png" width="55" class="mb-1"></a>
                    <a href="{{ getContent('facebook_link') | removeHTMLTags }}" class="footer-social">
                        <img src="/images/socialMedia/facebook.png" width="55"></a>
                    <a href="{{ getContent('twitter_link') | removeHTMLTags }}" class="footer-social">
                        <img src="/images/socialMedia/twitter.png" width="55"></a>
                    <a href="{{ getContent('linkedin_link') | removeHTMLTags }}" class="footer-social">
                        <img src="/images/socialMedia/linkedIn.png" width="55" style="margin-bottom: 3px;"></a>
                </div>

                <%--<div>
                    <a href="{{ getContent('facebook_link') | removeHTMLTags }}" class="footer-social">
                        <i class="fa fa-link"></i></a>
                    <a href="{{ getContent('twitter_link') | removeHTMLTags }}" class="footer-social">
                        <i class="fa fa-link"></i></a>
                    <a href="{{ getContent('linkedin_link') | removeHTMLTags }}" class="footer-social">
                        <i class="fa fa-link"></i></a>
                </div>--%>

            </div>
        </div>
    </div>

    <div class="row pt-3 mt-5">
        <div class="col text-center">
            <strong><small class="footer-developer"><i class="fa-solid fa-display"></i> Develop By : Amin Safiari <i class="fa-solid fa-keyboard"></small></i></strong>
        </div>
    </div>

</footer>