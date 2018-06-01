package com.ale.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
/**
 * @author alewu
 * @date 2018-01-28
 * @@description Tag 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class Tag extends BaseEntity implements Serializable {
    /**  */
    private Long tagId;
    /**  */
    private String tagName;


}
