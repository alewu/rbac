//package com.ale.filter;
//
//import com.ale.common.exception.custom.JwtTokenMissingException;
//import com.ale.other.JwtUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 职责：拦截请求时，验证token
// * 描述：
// *
// * @author alewu
// * @date 2018/6/4
// */
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Resource
//    private JwtUtils jwtUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//        final String authTokenStart = "Bearer ";
//        if (header == null || !header.startsWith(authTokenStart)) {
//            // 不按规范,不允许通过验证
//            throw new JwtTokenMissingException("No JWT token found in request headers");
//        }
//        String authToken = header.substring(7);
//        String username = jwtUtils.getUsernameFromToken(authToken);
//        logger.info(String.format("Checking authentication for user %s.", username));
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // It is not compelling necessary to load the use details from the database. You could also store the
//            // information
//            // in the token and read it from it. It's up to you ;)
//            // UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            UserDetails userDetails = jwtUtils.getUserFromToken(authToken);
//
//            // For simple validation it is completely sufficient to just check the token integrity. You don't have to
//            // call the database compellingly. Again it's up to you ;)
//            if (jwtUtils.validateToken(authToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
//                        (userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                logger.info(String.format("Authenticated user %s, setting security context", username));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
