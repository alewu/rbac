package com.ale.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alewu
 * @date 2018-01-28
 * @description Post 数据库表对应的实体类
*/

@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Post extends BaseEntity implements Serializable {
    /**  */
    private Long postId;
    /**  */
    private Long userId;
    /**  */
    private String postSubject;
    /**  */
    private Date publishDate;


}
