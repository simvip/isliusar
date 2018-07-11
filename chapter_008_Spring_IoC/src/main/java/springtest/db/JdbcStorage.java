package springtest.db;

import org.springframework.stereotype.Component;
import springtest.models.User;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
@Component
public class JdbcStorage implements Storage {
    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User findById(User user) {
        return null;
    }
}
