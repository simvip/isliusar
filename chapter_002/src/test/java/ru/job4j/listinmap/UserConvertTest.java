package ru.job4j.listinmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 31.08.2017.
 */
public class UserConvertTest {

    /**
     * Test 1.
     *
     * @throws Exception
     */
    @Test
    public void process() {
        List list = new ArrayList<User>();
        list.add(new User(1, "Petrov", "Chicago"));
        list.add(new User(2, "Ivanov", "Kiev"));
        list.add(new User(3, "Sidorov", "Moscow"));

        Map expected = new HashMap<Integer, User>();
        expected.put(1, new User(1, "Petrov", "Chicago"));
        expected.put(2, new User(2, "Ivanov", "Kiev"));
        expected.put(3, new User(3, "Sidorov", "Moscow"));

        UserConvert userConvert = new UserConvert();
        Map rezult = userConvert.process(list);
        assertEquals(rezult, expected);
    }

}