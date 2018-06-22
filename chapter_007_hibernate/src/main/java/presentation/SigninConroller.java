package presentation;


import com.google.gson.Gson;
import logic.ValidateUser;
import models.User;
import org.json.JSONObject;

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

    private static final ValidateUser LOGIC = ValidateUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if (ajax) {

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
                case 1:
                    User user = LOGIC.findByEmail(inputJson.getString("email").trim());

                    Map<String, String> responseMap = new LinkedHashMap<>();
                    responseMap.put("validate", "false");

                    if (user != null) {
                        responseMap.put("validate", "true");
                        responseMap.put("user", new Gson().toJson(user));
                        HttpSession session = req.getSession(true);
                        session.setAttribute("login", user.getLogin().trim());
                        session.setAttribute("id", user.getId());
                        session.setAttribute("name", user.getName().trim());
                        session.setAttribute("email", user.getEmail().trim());
                    }

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(new Gson().toJson(responseMap));
                    break;
            }

        }
    }
}
