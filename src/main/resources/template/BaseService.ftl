package ${packageName}.service;

/**
 * 基础接口
 * @author ${author}
 * @date ${date}
 */
public interface BaseService<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(Object obj);



}