package persistent;

import java.util.List;

/**
 * Created by Ivan Sliusar on 31.05.2018.
 * Red Line Soft corp.
 */
public interface Store<T> {
    void add(T entity);
    void update(T entity);
    boolean delete(T entity);
    List<T> findAll();
    T findById(int id);
}
