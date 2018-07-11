package springtest.db;

import springtest.models.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
@Component
public class MemoryStorage implements Storage {
    private final Map<Integer, User> userList;

    public MemoryStorage() {
        this.userList = new ConcurrentHashMap<>();
    }

    @Override
    public void add(User user) {
        if (user.getId() == null) {
            user.setId(this.userList.size() + 1);
        }
        this.userList.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        this.userList.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        this.userList.remove(user.getId());
    }

    @Override
    public User findById(User user) {
        return this.userList.get(user.getId());
    }
}
