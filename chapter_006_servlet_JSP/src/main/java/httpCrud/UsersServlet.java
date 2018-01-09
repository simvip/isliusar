package httpcrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Sliusar on 08.01.2018.
 * Red Line Soft corp.
 */
public class UsersServlet extends HttpServlet {
    /**
     * User store db.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("All users: ");
        writer.append(System.lineSeparator());
        List<User> allUsers = users.getAllUsers();
        for (User user :allUsers) {
            writer.append(user.toString());
            writer.append(System.lineSeparator());
        }
        writer.flush();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.delete(new User("Alex1", "AlexXXX1", "Alex@gmail.com", new Date()));
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.add(new User("Alex1", "AlexXXX1", "Alex@gmail.com", new Date()));
        users.add(new User("Alex2", "AlexXXX2", "Alex@gmail.com", new Date()));
        users.add(new User("Alex3", "AlexXXX3", "Alex@gmail.com", new Date()));
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.update(new User("AlexUPDATE", "AlexXXX1", "AlexUPDATE@gmail.com", new Date()));
        doGet(req, resp);
    }
}
