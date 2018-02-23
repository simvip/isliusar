package mvc.controls;


import com.google.gson.Gson;
import mvc.models.Dislocation;
import mvc.models.Role;
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
import java.util.Date;
import java.util.List;


/**
 * Created by Ivan Sliusar on 09.01.2018.
 * Red Line Soft corp.
 */
public class UsersController extends HttpServlet {
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

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if (ajax) {
            String json = new Gson().toJson(store.getAllUsers());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } else {
            HttpSession session = req.getSession();
            req.setAttribute("UserRole", session.getAttribute("role"));
            req.setAttribute("Users", store.getAllUsers());
            req.setAttribute("Roles", Role.getAllRole());
            req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
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

            JSONObject jsObject = new JSONObject(jb.toString());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            int command = jsObject.getInt("command");

            switch (command) {
                case 0: //get Region
                    List<Dislocation> dislocation = store.getDislocation("tbl_region", jsObject.getInt("id_county"));
                    String s = new Gson().toJson(dislocation);
                    resp.getWriter().write(s);

                    break;
                case 1: // create user

                    store.add(new User(
                            jsObject.getString("login"),
                            jsObject.getString("login"),
                            jsObject.getString("email"),
                            new Date(),
                            Role.valueOf(jsObject.getString("role")),
                            jsObject.getInt("country"),
                            jsObject.getInt("region")
                    ));
                    JSONObject jsonToReturn = new JSONObject();
                    jsonToReturn.put("create", "true");
                    resp.getWriter().write(jsonToReturn.toString());
                    break;
                case 2: // delete user
                    store.delete(jsObject.getString("login"));
                    resp.getWriter().write(
                            new JSONObject()
                                    .put("delete", "true")
                                        .toString());
                    break;
                case 3: // update user
                    store.update(new User(
                            jsObject.getString("login"),
                            jsObject.getString("login"),
                            jsObject.getString("email"),
                            new Date(),
                            Role.valueOf(jsObject.getString("role")),
                            jsObject.getInt("country"),
                            jsObject.getInt("region")
                    ));
                    JSONObject jsonToReturnUpd = new JSONObject();
                    jsonToReturnUpd.put("create", "true");
                    resp.getWriter().write(jsonToReturnUpd.toString());
                    break;
            }

            //resp.getWriter().write(new Gson().toJson(responseMap));
            int a = 1;
        } else {
            String whatToDo = req.getParameter("whatToDo");

            if ("delete".equals(whatToDo)) {
                store.delete(req.getParameter("login"));

            } else if ("add".equals(whatToDo)) {
                store.add(new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        new Date(),
                        Role.valueOf(req.getParameter("role")),
                        0,
                        0
                ));
            }

            resp.sendRedirect(String.format("%s", req.getContextPath()));
        }
    }
}
