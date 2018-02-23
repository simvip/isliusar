package mvc.models;


import java.util.Date;

/**
 * Created by Ivan Sliusar on 08.01.2018.
 * Red Line Soft corp.
 */
public class User {
    public int getId_country() {
        return id_country;
    }

    public int getId_region() {
        return id_region;
    }

    /**
     * Name.
     */

     private String name;

    /**
     * Getter city.
     * @return String
     */
    public String getRegion() {
        return region;
    }

    /**
     * Getter country.
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Region.
     */
    private String region;

    /**
     * Country.
     */
    private int id_country;

    /**
     * Region.
     */
    private int id_region;

    /**
     * Country.
     */
    private String country;

    /**
     * Users role.
     */
    private Role role;
    /**
     * Login.
     */
    private String login;
    /**
     * email.
     */
    private String email;
    /**
     * Create user date.
     */
    private Date createDate;

    /**
     * Getter name.
     *
     * @return String.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter login.
     *
     * @return String
     */
    public String getLogin() {
        return login.trim();
    }

    /**
     * Getter login.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter role.
     * @return Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter create date.
     *
     * @return Date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Construct.
     *
     * @param name       String
     * @param login      String
     * @param email      String
     * @param createDate Date
     */
    public User(String name, String login, String email, Date createDate, Role role, String country, String region) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
        this.country = country;
        this.region = region;
    }

    public User(String name, String login, String email, Date createDate, Role role, int country, int region) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
        this.id_country = country;
        this.id_region = region;
    }

    /**
     * Override toString.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "name=" + name + "login=" + login;
    }
}
