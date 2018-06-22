package presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import logic.ValidateUser;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Crud;
import utils.JsonParser;
import utils.ThrowInPresentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
public class UserServlet extends HttpServlet {
    private static final ValidateUser LOGIC = ValidateUser.getInstance();
    private static final JsonParser JSON_UTIL = JsonParser.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<User> users = LOGIC.findAll();
        JSONArray jsonUsers = new JSONArray();
        for (User user : users) {
            jsonUsers.put(user.getId(), JSON_UTIL.toString(user));
        }

        resp.getWriter().write(jsonUsers.toString());
    }

    /**
     * Override doPost
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                try {
                    throw new ThrowInPresentation("Don`t recognize command");
                } catch (ThrowInPresentation throwInPresentation) {
                    throwInPresentation.printStackTrace();
                }
        }
        resp.getWriter().write(outJSON.toString());

    }

    private String parseRequestToJson(HttpServletRequest req) {
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
        return jb.toString();
    }
}