package my.jpa.service;

import my.jpa.models.User;
import my.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
@Service
public class ValidateUser {
    @Autowired
    private UserRepository repo;

    private ValidateUser() {}

    public void add(User user) {
        repo.save(user);
    }

    public void update(User user) {
        repo.save(user);}

    public void delete(User user) {
        User deleteUser = findByID(user.getId());
        if (deleteUser != null)
            repo.delete(deleteUser);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        repo.findAll().forEach(u->users.add(u));
        return users;
    }

    public User findByEmail(String email) {
        return (User) repo.findByEmail(email);
    }

    public User findByID(int id) {
        return repo.findById(Integer.valueOf(id)).get();
    }
}
