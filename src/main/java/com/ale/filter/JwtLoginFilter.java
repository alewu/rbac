package com.ale.filter;

import com.ale.entity.AccountCredentials;
import com.ale.entity.JSONResult;
import com.ale.provider.JwtAuthenticationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @author zhaoxinguo on 2017/9/12.
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JwtLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    /**
     * 接收并解析用户凭证
     * @param req 请求
     * @param res 响应
     * @return Authentication 未认证的包含用户提交的信息的“令牌”
     * @throws AuthenticationException 认证异常
     * @throws IOException io异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException {
        // JSON反序列化成 AccountCredentials
        AccountCredentials credentials = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
        // 认证管理器
        logger.info(credentials);
        AuthenticationManager authenticationManager = getAuthenticationManager();
        // 组装 UsernamePasswordAuthenticationToken (未认证)
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        // 返回一个验证令牌
        return authenticationManager.authenticate(authentication);
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     * @param req 请求
     * @param res 响应
     * @param chain 过滤器链
     * @param auth 认证
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        JwtAuthenticationUtil.addAuthentication(res, auth.getName());
    }

    /**
     * 用户登录后，这个方法会被调用，返回错误信息
     * @param req 请求
     * @param res 响应
     * @param failed 异常
     * @throws IOException io异常
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException {
        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        res.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!", ""));
    }
}
