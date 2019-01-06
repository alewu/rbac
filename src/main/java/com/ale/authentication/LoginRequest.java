package com.ale.authentication;

import lombok.Data;


import java.io.Serializable;
/**
 * @author alewu
 * @date 2018/2/25
 * @description 账号凭证
 */
@Data
public class LoginRequest implements Serializable{
    private String username;
    private String password;
}
