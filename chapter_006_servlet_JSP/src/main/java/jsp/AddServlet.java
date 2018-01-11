package jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class AddServlet extends HttpServlet {
    /**
     * Logger, now not use.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * User storage.
     */
    private final UserStore users = UserStore.getInstance();

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
        resp.setContentType("text/html");
        users.add(new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new java.util.Date()
        ));
        resp.sendRedirect(String.format("%s/index.jsp",req.getContextPath()));
    }
}
