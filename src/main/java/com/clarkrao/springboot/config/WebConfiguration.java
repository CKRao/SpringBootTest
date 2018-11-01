package com.clarkrao.springboot.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/25 22:02
 * @Description:
 */
//@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
//

        return registration;
    }

    /**
     * 自定义Filter
     */
    public class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // 初始化操作

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain filterChain) throws IOException, ServletException {
//            过滤操作
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
            // 结束操作
        }
    }
}
