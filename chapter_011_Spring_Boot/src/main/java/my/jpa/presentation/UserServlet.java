package my.jpa.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import my.jpa.service.ValidateUser;
import my.jpa.models.User;
import my.jpa.utils.Crud;
import my.jpa.utils.JsonParser;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import my.jpa.utils.ThrowInPresentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
@Controller
public class UserServlet {
    private static final Logger LOGGER = Logger.getLogger(UserServlet.class);
    @Autowired
    private ValidateUser LOGIC;
    private static final JsonParser JSON_UTIL = JsonParser.getInstance();


    @RequestMapping(value="/users",method = RequestMethod.GET)
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<User> users = LOGIC.findAll();
        JSONArray jsonUsers = new JSONArray();
        for (User user : users) {
            jsonUsers.put(user.getId(), JSON_UTIL.toString(user));
        }

        resp.getWriter().write(jsonUsers.toString());
        return "index";
    }

    /**
     * Override doPost
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/users",method = RequestMethod.POST)
    protected String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject outJSON = new JSONObject();
        outJSON.append("event", "done");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JSONObject inJSON = new JSONObject(parseRequestToJson(req));
        User user = JSON_UTIL.fromJson(inJSON.getJSONObject("user").toString(), new TypeReference<User>(){});

        String inputCommand = inJSON.getJSONObject("service").getString("command");
        switch (Crud.valueOf(inputCommand)) {
            case CREATE:
                LOGIC.add(user);
                break;
            case UPDATE:
                LOGIC.update(user);
                break;
            case DELETE:
                LOGIC.delete(user);
                break;
            default:
                LOGGER.error(new ThrowInPresentation("Don`t recognize command"));
        }
        resp.getWriter().write(outJSON.toString());
        return "index";
    }

    private String parseRequestToJson(HttpServletRequest req) {
        // Handle ajax (JSON or XML) response.
        StringBuilder jb = new StringBuilder();
        String line = "";

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception ex) {
            LOGGER.error("We have a problem with parse request JSON",ex);
        }
        return jb.toString();
    }
}