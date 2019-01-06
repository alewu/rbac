//package com.ale.authentication;
//
//import com.ale.authorization.GrantedAuthorityImpl;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.ArrayList;
//
///**
//  * @author alewu
//  * @date 2018/2/13
//  * @description 自定义身份验证提供者
//  */
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // 获取认证的用户名 & 密码
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        // 认证逻辑
//        if ("admin".equals(name) && "123456".equals(password)) {
//            // 这里设置权限和角色
//            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
//            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
//            // 将已通过验证的用户信息封装成 UsernamePasswordAuthenticationToken 对象并返回
//            return new UsernamePasswordAuthenticationToken(name, password, authorities);
//        }else {
//            throw new BadCredentialsException("密码错误~");
//        }
//    }
//
//    /**
//     * 是否可以提供输入类型的认证服务
//     * @param authentication 认证
//     * @return boolean
//     */
//    @Override
//    public boolean supports(Class<?> authentication) {
//        // 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
