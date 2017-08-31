package ru.job4j.listinmap;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 31.08.2017.
 */
public class UserConvert {
    /**
     * Convert User list to HashMap.
     *
     * @param list List
     * @return HasMap
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap rezult = new HashMap<Integer, User>();
        for (User user : list) {
            rezult.put(user.getId(), user);
        }
        return rezult;
    }
}

