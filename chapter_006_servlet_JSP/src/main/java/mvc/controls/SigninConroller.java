package mvc.controls;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mvc.models.User;
import mvc.models.UserStore;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.EchoServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class SigninConroller extends HttpServlet {
    /**
     * Logger, not use now.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * User storage.
     */
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req, resp);
        req.getRequestDispatcher("login.html").forward(req, resp);
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
        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if (ajax) {
            // Handle ajax (JSON or XML) response.

            StringBuilder jb = new StringBuilder();
            String line = "";
            try {
                BufferedReader reader = req.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            JSONObject inputJson = new JSONObject(jb.toString());

            int command = inputJson.getInt("command");
            switch (command) {
                case 1: //login

                    User user = store.getUserByLogin(inputJson.getString("login"));

                    Map<String, String> responseMap = new LinkedHashMap<>();
                    responseMap.put("validate", "false");

                    if (user != null) {
                        responseMap.put("validate", "true");
                        responseMap.put("user", new Gson().toJson(user));
                        HttpSession session = req.getSession();
                        session.setAttribute("login", user.getLogin());
                        session.setAttribute("role", user.getRole());
                    }

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(new Gson().toJson(responseMap));
                    break;
            }
        } else {
            // Handle regular (JSP) response.
            String login = req.getParameter("login");
            HttpSession session = req.getSession();
            if (UserStore.getInstance().isCredential(login)) {


                session.setAttribute("login", login);
                session.setAttribute("role", UserStore.getInstance().getRole(login).trim());

                resp.sendRedirect(String.format("%s", req.getContextPath()));
            } else {
                req.setAttribute("error", "Credetional invalid");
                session.setAttribute("error", true);
                doGet(req, resp);
            }
        }


    }
}
