package ru.job4j.sort;

import java.util.*;

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

    /**
     * Sort by name.
     *
     * @param notSorted List
     * @return List
     */
    public List<User> sortNameLength(List<User> notSorted) {
        Collections.sort(notSorted, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });

        return notSorted;
    }

    /**
     * Sort by all fields.
     *
     * @param notSorted List
     * @return List
     */
    public List<User> sortByAllFields(List<User> notSorted) {
        Collections.sort(notSorted, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int sortByName = o1.getName().compareTo(o2.getName());
                return sortByName != 0 ? sortByName : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return notSorted;
    }
}
