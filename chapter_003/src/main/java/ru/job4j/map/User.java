package ru.job4j.map;

import java.util.Calendar;

/**
 * Created by Ivan Sliusar on 06.10.2017.
 * Red Line Soft corp.
 */
public class User {
    /**
     * Name user.
     */
    private String name;
    /**
     * Amount of children.
     */
    private int children;
    /**
     * Birthday date.
     */
    private Calendar birthday;


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    /**
     * Construct.
     * @param name String
     * @param children int
     * @param birthday Calendar
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
