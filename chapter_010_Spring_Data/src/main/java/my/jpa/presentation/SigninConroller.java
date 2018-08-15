package my.jpa.presentation;


import com.google.gson.Gson;
import my.jpa.service.ValidateUser;
import my.jpa.models.User;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
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
@Component
public class SigninConroller {
    private static final Logger logger = Logger.getLogger(SigninConroller.class);
    @Autowired
    private ValidateUser LOGIC;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        if (ajax) {
            StringBuilder jb = new StringBuilder();
            String line = "";
            try {
                BufferedReader reader = req.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception ex) {
                logger.error("We have a problem with parse request JSON", ex);
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
