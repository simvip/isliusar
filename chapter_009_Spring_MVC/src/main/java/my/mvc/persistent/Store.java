package my.mvc.persistent;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Sliusar on 31.05.2018.
 * Red Line Soft corp.
 */
public interface Store<T> {
    void add(T entity);
    void update(T entity);
    boolean delete(T entity);
    List<T> findAll();
    List<T> findAllByParam(Map<String,Object> param);
    T findById(int id);
}
