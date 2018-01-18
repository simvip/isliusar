package mvc.controls;


import mvc.models.User;
import mvc.models.UserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class UsersController extends HttpServlet {
    /**
     * Logger, not use now.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * User storage.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Users",users.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req,resp);
    }

    /**
     * Do post.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String whatToDo = req.getParameter("whatToDo");

        if ("delete".equals(whatToDo)) {
            users.delete(req.getParameter("login"));

        }else if ("add".equals(whatToDo)) {
            users.add(new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date()
            ));
        }

        resp.sendRedirect(String.format("%s",req.getContextPath()));
    }
}
