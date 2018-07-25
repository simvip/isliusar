package springtest.db;

import springtest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
@Component
public class UserStorage {
    private final Storage storage;

    @Autowired
    public UserStorage(@Qualifier("jdbc")final Storage storage) {
        this.storage = storage;
    }


    public void add(User user) {
        storage.add(user);
    }


    public void update(User user) {
        storage.update(user);
    }


    public void delete(User user) {
        storage.delete(user);
    }


    public User findById(int id) {
        return storage.findById(id);
    }
}
