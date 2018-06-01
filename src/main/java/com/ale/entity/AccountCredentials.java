package com.ale.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
/**
  * @author alewu
  * @date 2018/2/25
  * @description 账号凭证
  */
@Data
public class AccountCredentials implements Serializable{
    private String username;
    private String password;
}
