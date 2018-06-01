package com.ale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable{

    /** 创建日期时间 **/
    @JsonIgnore
    private Date gmtCreate;
    /** 更新日期时间 **/
    @JsonIgnore
    private Date gmtModified;
}
