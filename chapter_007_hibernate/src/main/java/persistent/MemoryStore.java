package persistent;


import models.User;

import java.util.List;

/**
 * Created by Ivan Sliusar on 31.05.2018.
 * Red Line Soft corp.
 */
public class MemoryStore implements Store<User> {
    private final static MemoryStore INSTANCE = new MemoryStore();

    public static Store getInstance() {
        return INSTANCE;
    }

    private MemoryStore() {
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}

