//package com.ale.filter;
//
//import com.ale.filter.JwtAuthenticationUtil;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
///**
//  * @author alewu
//  * @date 2018/2/13
//  * @description
// * token的校验
// * 从http头的Authorization 项读取token数据，然后用 Jwts 包提供的方法校验token的合法性。
// * 如果校验通过，就认为这是一个取得授权的合法请求
// */
//
//public class JwtAuthenticationFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
//        Authentication authentication = JwtAuthenticationUtil.getAuthentication((HttpServletRequest) req);
//        logger.info(authentication);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(req, res);
//    }
//}
