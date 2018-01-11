package httpcrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 08.01.2018.
 * Red Line Soft corp.
 */
public class UserStore {
    /**
     * Connection to base.
     */
    private static Connection connection;
    /**
     * Instance user store;
     */
    private static final UserStore INSTANCE = new UserStore();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud", "postgres", "postgres");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }

        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS (ID serial primary key, NAME CHAR(50),LOGIN CHAR(50), EMAIL CHAR(50), CREATEDATE DATE)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Can't create users table.");
            e.printStackTrace();
        }

    }


    /**
     * Add user.
     */
    public static void add(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO USERS(NAME,LOGIN,EMAIL,CREATEDATE) VALUES (?,?,?,?)")){

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, new Date(user.getCreateDate().getTime()));
            stmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user.
     */
    public static void update(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE USERS SET NAME = ?, EMAIL = ?  WHERE USERS.LOGIN = ?")){

            connection.setAutoCommit(false);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getLogin());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user.
     */
    public static void delete(User user) {

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM USERS WHERE USERS.LOGIN = ?")){
            stmt.setString(1, user.getLogin());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     */
    public static List<User> getAllUsers() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;")){

            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                rezult.add(
                        new User(
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getString("email"),
                                rs.getDate("createDate")
                        ));
            }

            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
     * private constructor.
     */
    private UserStore() {

    }

    /**
     * Get instanse
     *
     * @return UserStore.
     */
    public static UserStore getInstance() {
        return INSTANCE;
    }
}
