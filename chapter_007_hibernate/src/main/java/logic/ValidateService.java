package logic;

import models.User;
import persistent.PostgreStore;
import persistent.Store;

import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();
    private static final Store STORE = PostgreStore.getInstance();

    public static ValidateService getInstance(){
        return INSTANCE;
    }
    private ValidateService() {
    }

    public void add(User user) {
        if (user.getId()==null)
            STORE.add(user);
        else
            STORE.update(user);
    };

    public void update(User user) {
        STORE.update(user);
    };

    public void delete(User user) {
        User deleteUser = (User) STORE.findById(user.getId());
        if (deleteUser!=null)
            STORE.delete(deleteUser);
    };

    public List<User> findAll() {
        return STORE.findAll();
    };

    public User findByID(int id) {
        return (User) STORE.findById(id);
    };
}
