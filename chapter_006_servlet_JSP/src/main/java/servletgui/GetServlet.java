package servletgui;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class GetServlet extends HttpServlet {
    /**
     * Logger, not use now.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * User storage.
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());


        List<User> allUsers = users.getAllUsers();
        StringBuilder sb = new StringBuilder("<table>");
        for (User user : allUsers) {
            sb.append("<tr>" +

                    "<td>" +
                    user.getLogin() +
                    "</td>" +

                    "<td>" +
                    user.getName() +
                    "</td>" +

                    "<td>" +
                    user.getEmail() +
                    "</td>" +

                    "<td>" +
                    "<form action='" + req.getContextPath() + "/updateusers' method='post'>" +
                    "<input type='hidden' name='status' value=redirectToDoGet />" +
                    "<input type='hidden' name='login' value=" + user.getLogin() + "/>" +
                    "<input type='submit' value='Update user'/>" +
                    "</form>" +
                    "</td>" +

                    "<td>" +
                    "<form action='" + req.getContextPath() + "/deleteusers' method='post'>" +
                    "<input type='hidden' name='login' value=" + user.getLogin() + "/>" +
                    "<input type='submit' value='Delete user'/>" +
                    "</form>" +
                    "</td>" +

                    "</tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action='" + req.getContextPath() + "/addusers' method='post'>\n" +
                "<p>Name : <input type=text' name='name'/></p>" +
                "<p>Login : <input type=text' name='login'/></p>" +
                "<p>Email : <input type=text' name='email'/></p>" +
                "<input type='submit' value='--------- Add user ---------'/>" +
                "</form>\n" +
                "<br>" +
                sb.toString() +
                "<br/>" +
                "</body>\n" +
                "</html>");

        writer.flush();
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
        doGet(req, resp);
    }
}
