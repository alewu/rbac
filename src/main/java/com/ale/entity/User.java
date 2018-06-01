package com.ale.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alewu
 * @date 2018-01-28
 * @@description User 数据库表对应的实体类
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {
    /**  */
    private Long userId;
    /**  */
    private String userName;
    /**  */
    private String password;

    private String authorities;
    /**  */
    private String phoneNum;
    /**  */
    private String email;


}
