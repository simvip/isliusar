package monitore;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 31.10.2017.
 * Red Line Soft corp.
 */
public class UserStorageTest {
    /**
     * Test.
     */
    @Test
    public void transfer() {

        UserStorage stoge = new UserStorage();
        User user1 = new User(1, 100);
        stoge.add(user1);
        User user2 = new User(2, 200);
        stoge.add(user2);

        stoge.transfer(1, 2, 50);
        assertThat(user2.getAmount(), is(250));
    }

}