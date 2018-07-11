package springtest.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import springtest.models.User;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
@Component
public class UserStorage {
    private final Storage storage;

    @Autowired
    public UserStorage(@Qualifier("memoryStorage") final Storage storage) {
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


    public User findById(User user) {
        return storage.findById(user);
    }
}
