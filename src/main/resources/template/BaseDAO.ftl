package ${packageName}.dao;


/**
 * 基础接口
 * @author ${author}
 * @date ${date}
 */
public interface BaseDAO<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(Object obj);



}