package com.ale.common.mvc;

/**
 * @author alewu
 * @date 2017-12-19
 * @description 基础接口
 */
public interface BaseService<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(Object obj);



}