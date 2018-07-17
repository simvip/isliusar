package springtest.db;

import springtest.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
public class UserStorageTest {
    private UserStorage storage;

    private User user1;
    private User user2;

    @Before
    public void intUser(){
        user1 = new User();
        user2 = new User();
        user1.setName("John");
        user2.setName("Mark");
    }

    @Before
    public void initStorage(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        storage = context.getBean(UserStorage.class);
    }

    @Test
    public void add() throws Exception {
        storage.add(user1);
        assertThat(user1,is(storage.findById(user1.getId())));
    }

    @Test
    public void update() throws Exception {
        storage.add(user1);
        user1.setName("Mik");
        storage.update(user1);
        assertThat(user1.getName(),is(storage.findById(user1.getId()).getName()));
    }

    @Test
    public void delete() throws Exception {
        storage.add(user1);
        storage.delete(user1);
        assertNull(storage.findById(user1.getId()));
    }

    @Test
    public void findById() throws Exception {
        storage.add(user1);
        assertThat(user1.getId(),is(storage.findById(user1.getId()).getId()));
    }

}