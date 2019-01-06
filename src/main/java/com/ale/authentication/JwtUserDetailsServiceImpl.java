package com.ale.authentication;

import com.ale.authorization.GrantedAuthorityImpl;
import com.ale.dao.PermissionDAO;
import com.ale.dao.RoleDAO;
import com.ale.dao.UserDAO;
import com.ale.entity.Permission;
import com.ale.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户标识获取用户
        User user = userDAO.loadUserByUsername(username);
        if (user == null) {
            log.debug("can not find user: " + username);
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        // 2. 获取用户角色，设置权限
        Boolean enabled = true;
        Boolean accountNonExpired = true;
        Boolean credentialsNonExpired = true;
        Boolean accountNonLocked = true;
        List<Permission> permissions = permissionDAO.listPermission();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission p : permissions) {
            if (p != null && p.getName() != null) {
                GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(p.getUrl(), p.getMethod());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new JwtUserDetails(user.getUserId(), username, user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

