package my.mvc.logic;

import my.mvc.models.User;
import my.mvc.persistent.PostgreUserStore;

import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
public class ValidateUser {

    private static final ValidateUser INSTANCE = new ValidateUser();
    private static final PostgreUserStore STORE = PostgreUserStore.getInstance();

    public static ValidateUser getInstance() {
        return INSTANCE;
    }

    private ValidateUser() {
    }

    public void add(User user) {
        if (user.getId() == null)
            STORE.add(user);
        else
            STORE.update(user);
    };



    public void update(User user) {
        STORE.update(user);
    };

    public void delete(User user) {
        User deleteUser = (User) STORE.findById(user.getId());
        if (deleteUser != null)
            STORE.delete(deleteUser);
    }

    ;

    public List<User> findAll() {
        return STORE.findAll();
    }

    ;

    public User findByEmail(String email) {
        return (User) STORE.findByEmail(email);
    }

    public User findByID(int id) {
        return (User) STORE.findById(id);
    }

    ;
}
