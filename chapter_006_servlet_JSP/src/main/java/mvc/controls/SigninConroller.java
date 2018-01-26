package mvc.controls;



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



/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class SigninConroller extends HttpServlet {
    /**
     * Logger, not use now.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req,resp);
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
        String login = req.getParameter("login");
        HttpSession session = req.getSession();
        if (UserStore.getInstance().isCredential(login)){


            session.setAttribute("login",login);
            session.setAttribute("role", UserStore.getInstance().getRole(login).trim());

            resp.sendRedirect(String.format("%s",req.getContextPath()));
        } else {
            req.setAttribute("error","Credetional invalid");
            session.setAttribute("error",true);
            doGet(req,resp);
        }


    }
}
