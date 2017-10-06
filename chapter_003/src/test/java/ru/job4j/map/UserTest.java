package ru.job4j.map;

import org.junit.Test;
import ru.job4j.generic.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 06.10.2017.
 * Red Line Soft corp.
 */
public class UserTest {

    @Test
    public void notOverrideEqualsAndHashCode() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 10);

        User user1 = new User("Bobby", 2, calendar);
        User user2 = new User("Bobby", 2, calendar);

        Map<User,Object> map = new HashMap<>();
        map.put(user1,user1);
        map.put(user2,user2);

        System.out.println(map.toString());


    }

}