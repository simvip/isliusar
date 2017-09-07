package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Admin on 06.09.2017.
 */
public class SortUserTest {

    /**
     * Test.
     */
    @Test
    public void sort() {
        User user1 = new User("Stas", 1);
        User user2 = new User("Stas", 2);
        User user3 = new User("Stas", 3);

        List<User> notSort = new ArrayList<>();
        notSort.add(user3);
        notSort.add(user1);
        notSort.add(user2);

        List<User> sort = new ArrayList<>();
        sort.add(user1);
        sort.add(user2);
        sort.add(user3);

        SortUser sortUser = new SortUser();
        List rezult = new ArrayList(sortUser.sort(notSort));

        assertThat(sort, is(rezult));

    }

    /**
     * Test.
     */
    @Test
    public void whenSortNameLengthThenSortListByName() {
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Инокентий", 20);


        List<User> notSort = new ArrayList<>();
        notSort.add(user1);
        notSort.add(user2);
        notSort.add(user3);


        List<User> sort = new ArrayList<>();
        sort.add(user2);
        sort.add(user1);
        sort.add(user3);

        SortUser sortUser = new SortUser();

        assertThat(sortUser.sortNameLength(notSort), is(sort));
    }

    /**
     * Test.
     */
    @Test
    public void whenSortByAllFieldsThenSortListByName() {
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 20);
        User user3 = new User("Иван", 30);
        User user4 = new User("Сергей", 20);


        List<User> notSort = new ArrayList<>();
        notSort.add(user1);
        notSort.add(user2);
        notSort.add(user3);
        notSort.add(user4);


        List<User> sort = new ArrayList<>();
        sort.add(user2);
        sort.add(user3);
        sort.add(user4);
        sort.add(user1);

        SortUser sortUser = new SortUser();

        List<User> rezult = sortUser.sortByAllFields(notSort);
        assertThat(rezult, is(sort));
    }

}