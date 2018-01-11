package servletgui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class UpdateServlet extends HttpServlet {
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

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +

                "<form action='" + req.getContextPath() + "/updateusers' method='post'>" +
                "<input type='hidden' name='status' value=UpdateUser />" +
                "<p>Login : <input type='text' name='login' value=" + findUser.getLogin() + " '/></p>" +
                "<p>Name  : <input type='text' name='name'  value=" + findUser.getName() + " '/></p>" +
                "<p>Email : <input type='text' name='email' value=" + findUser.getEmail() + " '/></p>" +
                "<input type='submit' value='--------- Update user ---------'/>" +
                "</form>" +

                "</body>" +
                "</html>");

        writer.flush();
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
                    new Date()
            ));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/getusers");
            dispatcher.forward(req, resp);
        }
    }
}
