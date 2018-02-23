package mvc.controls;

import mvc.models.Role;
import mvc.models.User;
import mvc.models.UserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class UpdateController extends HttpServlet {
    /**
     * Logger, now not use.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * Store user.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Do Get.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      ServletException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User findUser = users.getUserByLogin(req.getParameter("login"));

        req.setAttribute("user",findUser);
        req.setAttribute("Roles",Role.getAllRole());

        req.getRequestDispatcher("WEB-INF/views/UpdateUserView.jsp").forward(req,resp);
    }

    /**
     * Do Post.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("redirectToDoGet".equals(req.getParameter("status"))) {
            doGet(req, resp);
        } else {
            users.update(new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Date(),
                    Role.valueOf(req.getParameter("role").trim()),
                    0,
                    0
            ));
            resp.sendRedirect(String.format("%s",req.getContextPath()));
        }
    }
}
