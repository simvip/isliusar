package httpcrud;


import java.util.Date;

/**
 * Created by Ivan Sliusar on 08.01.2018.
 * Red Line Soft corp.
 */
public class User {
    /**
     * Name.
     */
    private String name;
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
        return login;
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
    public User(String name, String login, String email, Date createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
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
