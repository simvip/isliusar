package ru.job4j.listinmap;

/**
 * Created by Admin on 31.08.2017.
 */
public class User {
    /**
     * Id user.
     */
    private int id;
    /**
     * name user.
     */
    public String name;
    /**
     * city user.
     */
    public String city;

    /**
     * Construct.
     * @param id int
     * @param name String
     * @param city String
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Getter id.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Override equals method.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return city != null ? city.equals(user.city) : user.city == null;
    }

    /**
     * Override hasCode method.
     * @return
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
