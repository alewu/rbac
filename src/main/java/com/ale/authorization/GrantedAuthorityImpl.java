package com.ale.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 授予认证对象的权限的实现类。
 *
 * @author alewu
 * @date 2018/6/6
 */
@Data
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {
    /**请求url*/
    private String url;
    /**请求方法*/
    private String method;

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }
}
