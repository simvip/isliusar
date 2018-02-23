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
        String query = "INSERT INTO USERS(NAME,LOGIN,EMAIL,CREATEDATE,ROLE,id_country,id_region) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, new Date(user.getCreateDate().getTime()));
            stmt.setString(5, user.getRole().name());
            stmt.setInt(6, user.getId_country());
            stmt.setInt(7, user.getId_region());
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
        String query = "UPDATE USERS SET NAME = ?, EMAIL = ?, ROLE = ? , id_country = ?, id_region = ? WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole().name());

            stmt.setInt(4, user.getId_country());
            stmt.setInt(5, user.getId_region());
            stmt.setString(6, user.getLogin());

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
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT \n" +
                "  users.id, \n" +
                "  users.name, \n" +
                "  users.login, \n" +
                "  users.email, \n" +
                "  users.createdate, \n" +
                "  users.role, \n" +
                "  tbl_country.name as country,\n" +
                "  tbl_region.name as region \n" +
                "  \n" +
                "FROM \n" +
                "  public.users, \n" +
                "  public.tbl_region, \n" +
                "  public.tbl_country\n" +
                "WHERE \n" +
                "  users.id_region = tbl_region.id AND\n" +
                "  users.id_country = tbl_country.id;\n");) {


            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getDate("createDate"),
                        Role.valueOf(rs.getString("role").trim()),
                        rs.getString("country"),
                        rs.getString("region")
                );
                rezult.add(user);
            }
            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all region
     */
    public List<Dislocation> getDislocation(String table, int id) {
        String query = "SELECT * FROM " + table + " WHERE " + table + ".id_parent = ?";
        ArrayList result = new ArrayList();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    result.add(
                            new Dislocation(rs.getInt("id"), rs.getString("name"))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNameDislocatonById(String table, int id){

        String query = "SELECT * FROM " + table + " WHERE " + table + ".id_parent = ?";
        ArrayList result = new ArrayList();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    return  rs.getString("name");
                }
            }
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
                            Role.valueOf(rs.getString("role").trim()),
                            0,
                            0
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCredential(String login) {
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

    public String getRole(String login) {
        String query = "SELECT * FROM USERS WHERE USERS.LOGIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
