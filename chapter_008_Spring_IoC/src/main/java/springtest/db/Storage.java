package springtest.db;

import springtest.models.User;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
public interface Storage {
    void add(User user);
    void update(User user);
    void delete(User user);
    User findById(int id);
}
