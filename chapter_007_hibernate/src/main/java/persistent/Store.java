package persistent;

import models.User;

import java.util.List;

/**
 * Created by Ivan Sliusar on 31.05.2018.
 * Red Line Soft corp.
 */
public interface Store<T> {
    void add(User user);
    void update(User user);
    boolean delete(User user);
    List<T> findAll();
    T findById(int id);
}
