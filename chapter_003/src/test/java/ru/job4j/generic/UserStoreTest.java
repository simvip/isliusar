package ru.job4j.generic;

import org.junit.Test;


/**
 * Created by Ivan Sliusar on 18.09.2017.
 * Red Line Soft corp.
 */
public class UserStoreTest {
    /**
     * Test 1.
     */
    @Test
    public void userStoreTest() {
        UserStore userStore = new UserStore(10);
        User john = new User("John");
        userStore.add(john);
        System.out.println(userStore.get(0));
        john.setName("Vasiliy");
        userStore.update(john);
        System.out.println(userStore.get(0));
        userStore.delete(john);
        System.out.println(userStore.get(0));
        //assertThat(simpleArray.get(0), is("Test"));
    }

    /**
     * Test 1.
     */
    @Test
    public void roleStoreTest() {
        RoleStore roleStore = new RoleStore(10);
        Role admin = new Role("admin");
        roleStore.add(admin);
        System.out.println(roleStore.get(0));
        admin.setRoleName("user");
        roleStore.update(admin);
        System.out.println(roleStore.get(0));
        roleStore.delete(admin);
        System.out.println(roleStore.get(0));

    }
}