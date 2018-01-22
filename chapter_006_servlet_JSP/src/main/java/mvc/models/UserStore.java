package mvc.models;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 08.01.2018.
 * Red Line Soft corp.
 */
public enum UserStore {
    INSTANCE;
    /**
     * Connection to base.
     */
    private Connection connection;


    {
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
    }


    /**
     * Add user.
     */
    public void add(User user) {
        String query = "INSERT INTO USERS(NAME,LOGIN,EMAIL,CREATEDATE,ROLE) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, new Date(user.getCreateDate().getTime()));
            stmt.setString(5, user.getRole().name());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user.
     */
    public void update(User user) {
        String query = "UPDATE USERS SET NAME = ?, EMAIL = ?, ROLE = ?  WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole().name());
            stmt.setString(4, user.getLogin());

            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user.
     */
    public void delete(String bylogin) {
        String query = "DELETE FROM USERS WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bylogin);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");) {


            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                rezult.add(
                        new User(
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getString("email"),
                                rs.getDate("createDate"),
                                Role.valueOf(rs.getString("role").trim())
                        ));
            }
            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get User by ID.
     *
     * @param login String.
     * @return User
     */
    public User getUserByLogin(String login) {
        String query = "SELECT * FROM USERS WHERE USERS.LOGIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    return new User(
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("email"),
                            rs.getDate("createDate"),
                            Role.valueOf(rs.getString("role").trim())
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCredential(String login){
        String query = "SELECT * FROM USERS WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRole(String login){
        String query = "SELECT * FROM USERS WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    return rs.getString("role");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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
