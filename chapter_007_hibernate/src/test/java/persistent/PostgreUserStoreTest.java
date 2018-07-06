package persistent;

import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 05.07.2018.
 * Red Line Soft corp.
 */
public class PostgreUserStoreTest {
    final PostgreUserStore store = PostgreUserStore.getInstance();
    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
    }

    @Test
    public void add() throws Exception {
        this.store.add(user);
        assertThat(user, is(this.store.findById(user.getId())));
    }

    @Test
    public void update() throws Exception {
        this.store.add(user);
        user.setName("Mark");
        this.store.update(user);
        assertThat(user.getName(), is(this.store.findById(user.getId()).getName()));
    }

    @Test
    public void delete() throws Exception {
        this.store.add(user);
        this.store.delete(user);
        assertNull(this.store.findById(user.getId()));
    }

    @Test
    public void findAll() throws Exception {
        this.store.add(user);
        assertTrue(this.store.findAll().contains(user));
    }

    @Test
    public void findById() throws Exception {
        this.store.add(user);
        assertThat(user.getId(), is(this.store.findById(user.getId()).getId()));
    }

    @Test
    public void findByEmail() throws Exception {
        this.store.add(user);
        assertThat(user.getEmail(), is(this.store.findById(user.getId()).getEmail()));
    }

}