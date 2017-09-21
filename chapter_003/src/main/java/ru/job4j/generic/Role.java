package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
public class Role extends Base {
    /**
     * Title role.
     */
    private String roleName;

    /**
     * Construct.
     *
     * @param roleName String
     */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Override.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.roleName;
    }

    /**
     * Getter.
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Setter.
     * @param roleName String
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
