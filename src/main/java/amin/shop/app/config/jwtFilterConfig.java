package amin.shop.app.config;

import amin.shop.app.config.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class jwtFilterConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public FilterRegistrationBean jwtFilterRegister() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(jwtRequestFilter);
        //this (*) mean any-think,when user SpringSecurity use (**).
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("jwtFilter");
        //use first filter(.setOrder(1)).because this filter is important.
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
