package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
public class User extends Base {
    /**
     * Name user.
     */
    public String name;

    /**
     * Construct.
     *
     * @param name String
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Override toString().
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
}
