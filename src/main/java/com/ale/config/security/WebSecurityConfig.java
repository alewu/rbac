package com.ale.config.security;

import com.ale.authorization.MyFilterSecurityInterceptor;
import com.ale.filter.JwtLoginFilter;
import com.ale.filter.JwtTokenFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * spring security 配置
 *
 * @author alewu
 * @date 2018/6/4
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "jwtUserDetailsService")
    private UserDetailsService jwtUserDetailsService;

    /**
     * 配置Spring Security的Filter链
     *
     * @param webSecurity WEB安全
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        // 设置不拦截规则
        webSecurity.ignoring().antMatchers("/static/**");
    }

    /**
     * HTTP请求安全处理
     *
     * @param httpSecurity HTTP安全
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 账号的 /register /login 的 POST 请求，都放行
                .antMatchers(HttpMethod.POST, "/accounts/*").permitAll()
                // 未匹配路径需要身份认证
                .anyRequest().authenticated()
                .and()
                // 在 UsernamePasswordAuthenticationFilter 前添加 JwtLoginFilter
                // 添加一个过滤器 将所有访问 /login 的请求交给 JwtLoginFilter 来处理, 添加在 UsernamePasswordAuthenticationFilter 之前
                .addFilterBefore(new JwtLoginFilter("/accounts/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法 添加在 UsernamePasswordAuthenticationFilter 之前
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                // 权限控制过滤拦截
                .addFilterBefore(new MyFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    /**
     * 配置user-detail服务
     *
     * @param auth 身份验证管理生成器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        //  auth.authenticationProvider(customAuthenticationProvider());
    }

    /**
     * 自定义认证提供器
     *
     * @return AuthenticationProvider
     */
//    @Bean
//    public AuthenticationProvider customAuthenticationProvider() {
//        return new CustomAuthenticationProvider();
//    }

}