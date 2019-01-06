package com.ale.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限管理过滤器
 * 这个类在中间起到的作用是无论是从登录页面发送的请求，还是直接访问你想要的资源，都会被这个过滤器拦截
 *
 * @author alewu
 * @date 2018/2/20
 */
@Slf4j
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    /**
     * 配置文件注入
     */
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(AccessDecisionManagerImpl myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws
            IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, filterChain);
        log.info("【权限管理过滤器】请求URL：{}", fi.getRequestUrl());
        invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        // fi里面有一个被拦截的url
        // 里面调用InvocationSecurityMetadataSourceImpl的getAttributes(Object object)这个方法获取fi对应的所有权限
        // 再调用AccessDecisionManagerImpl的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (Exception e) {
            log.error("【权限管理过滤器】【异常】{}：{}", e.getMessage(), e);
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {}

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        // 权限配置资源管理器
        return this.securityMetadataSource;
    }


}
