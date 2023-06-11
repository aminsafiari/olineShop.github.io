package amin.shop.app.config.filters;

import amin.shop.app.config.JwtTokenUtil;
import amin.shop.app.helper.exceptions.JwtTokenException;
import amin.shop.app.helper.uimodels.UserVM;
import amin.shop.app.services.people.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
//Filter type be should javax.servlet.
public class JwtRequestFilter implements Filter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //exclude means delete.
    private List<String> excludeUrls;
    //this code new add to code and security bug.
    //excludeContainsUrls -> the end of url is variable id or (...); <<name excludeContainsUrls default define>>.
    private List<String> excludeContainsUrls;


    //once call at initial start, be run for Implementation exception-list.
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        //To ignores these APIs, when missing jwt token, only JSESSIONID(java token).
        //my URL not dynamic.
        excludeUrls = new ArrayList<>();
        excludeContainsUrls = new ArrayList<>();
        //excludeContainsUrls -> the end of url is variable id or (...).
        excludeContainsUrls.add("/api/utils/upload/files/");
        excludeContainsUrls.add("/api/blog/info/");
        excludeContainsUrls.add("/api/product/getAll/");
        excludeContainsUrls.add("/api/product/info/");
        excludeContainsUrls.add("/api/productsSearch/");
        excludeContainsUrls.add("/api/product/search");

        excludeUrls.add("/api/user/login");
        excludeUrls.add("/api/color/");
        //for show images in fileManager.
        excludeUrls.add("/api/nav/");
        excludeUrls.add("/api/slider/");
        excludeUrls.add("/api/product/newProducts/");
        excludeUrls.add("/api/product/popularProducts/");
        excludeUrls.add("/api/product/cheapestProducts/");
        excludeUrls.add("/api/product/expensiveProducts/");
        excludeUrls.add("/api/content/getAllData/");
        excludeUrls.add("/api/blog/getAllData");
        excludeUrls.add("/api/productCategory/");
        excludeUrls.add("/api/payment/");
        excludeUrls.add("api/invoice/find");
        excludeUrls.add("api/user/getUserInfo");
    }

    //doFilter work stuff in description.
    //my Filter check token.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //we use token in Header(Authorization).
        //first work is read header for exist token or no and return error and throw it out.
        try {
            String url = ((HttpServletRequest) request).getRequestURI().toLowerCase();
            //<<.startWith(x)>> mean first start by once excludeUrl.
            if (excludeUrls.stream().anyMatch(x -> url.equals(x.toLowerCase())) ||
                    excludeContainsUrls.stream().anyMatch(x -> url.startsWith(x.toLowerCase()))) {
                chain.doFilter(request, response);
                return;
            }

            //we need cast because not exist method (.getHeader()), for this we cast to (HttpServletRequest) that Inheritance from ServletRequest.
            String requestTokenHeader = ((HttpServletRequest) request).getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("Request token header does not set!");

            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new JwtTokenException("username can not resolve!");

            UserVM userVM = new UserVM(userService.getByUsername(username));

            if (!jwtTokenUtil.validateToken(token, userVM))
                throw new JwtTokenException("invalid token!");

            chain.doFilter(request, response);

        } catch (JwtTokenException ex) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized!");
        } catch (ExpiredJwtException ex) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_EXPECTATION_FAILED, ex.getMessage());
        } catch (Exception ex) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
