package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Admin on 06.09.2017.
 */
public class SortUser {
    /**
     * Sort user.
     *
     * @param notSorted List
     * @return Set
     */
    public Set<User> sort(List<User> notSorted) {
        Set<User> users = new TreeSet<>();
        users.addAll(notSorted);
        return users;
    }
}
