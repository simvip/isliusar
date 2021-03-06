package ru.job4j.sort;

/**
 * Created by Admin on 06.09.2017.
 */
public class User implements Comparable {
    /**
     * name.
     */
    private String name;

    public String getName() {
        return name;
    }

    /**
     * Getter.
     *
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * age.
     */

    private int age;

    /**
     * Construct.
     *
     * @param name String
     * @param age  int
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Comparator.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        User compareUser = (User) o;
        int rezultCompareName = this.name.compareTo(compareUser.name);
        return rezultCompareName !=0 ? rezultCompareName : Integer.compare(this.age, compareUser.getAge());
    }

    /**
     * equals for hash table.
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;

    }

    /**
     * Hash code.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
