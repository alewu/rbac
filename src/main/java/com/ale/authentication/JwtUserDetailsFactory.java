//package com.ale.other;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public final class JwtUserDetailsFactory {
//
//    private JwtUserDetailsFactory() {}
//
//    public static JwtUserDetails create(User user) {
//        return new JwtUserDetails(
//                user.getUsername(),
//                user.getPassword(),
//                user.getAuthorities()
//               // mapToGrantedAuthorities(user.getRoles())
//        );
//    }
//
//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
//        return authorities.stream()
//                          .map(SimpleGrantedAuthority::new)
//                          .collect(Collectors.toList());
//    }
//
//}