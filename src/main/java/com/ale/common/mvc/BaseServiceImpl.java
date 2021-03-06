package com.ale.common.mvc;

import org.springframework.stereotype.Service;

/**
 * @author alewu
 * @date 2017-12-19
 * @description 基础服务层
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDAO<T> baseDAO;

    public void setBaseDAO(BaseDAO<T> baseDao) {
        this.baseDAO = baseDao;
    }

    @Override
    public int insert(T t) {
        return baseDAO.saveOne(t);
    }

    @Override
    public int delete(String id) {
        return baseDAO.removeOne(id);
    }

    @Override
    public int update(T t) {
        return baseDAO.updateOne(t);
    }

    @Override
    public T get(Object obj) {
        return baseDAO.getOne(obj);
    }


}

