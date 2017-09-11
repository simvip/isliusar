package ru.job4j.bank;

/**
 * Created by Ivan Sliusar on 11.09.2017.
 * Red Line Soft corp.
 */
public class User {
    /**
     * name.
     */
    String name;
    /**
     * id passport.
     */
    String passport;

    /**
     * Construct.
     * @param name String
     * @param passport String
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Getter Name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter Passport.
     * @return String
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Equals.
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return passport != null ? passport.equals(user.passport) : user.passport == null;

    }

    /**
     * Hash code.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
