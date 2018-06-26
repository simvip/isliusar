package presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import logic.ValidateCar;
import logic.ValidateFile;
import logic.ValidateItem;
import logic.ValidateUser;
import models.FileImage;
import models.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Crud;
import utils.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ivan Sliusar on 24.04.2018.
 * Red Line Soft corp.
 */
public class ItemServlet extends HttpServlet {
    /**
     * Instance validate layer.
     */
    private static final ValidateItem LOGIC_ITEM = ValidateItem.getInstance();
    private static final ValidateFile LOGIC_FILE = ValidateFile.getInstance();
    private static final ValidateUser LOGIC_USER = ValidateUser.getInstance();
    private static final ValidateCar  LOGIC_CAR  = ValidateCar.getInstance();
    private static final JsonParser JSON_PARSER = JsonParser.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JSONObject inputJSON = new JSONObject(parseRequestToJson(req));
        String command = inputJSON.getString("command");

        Boolean dataReceived = false;
        JSONObject outJSON = new JSONObject();

        switch (Crud.valueOf(command)) {
            case FIND_ALL:
                List<Item> allItems;
                if (inputJSON.has("queryParam") && !inputJSON.isNull("queryParam")) {
                    Map<String, String> mapParam = new HashMap<>();
                    JSONArray jArray = inputJSON.getJSONArray("queryParam");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jb = jArray.getJSONObject(i);
                        mapParam.put(jb.getString("name"), jb.getString("value"));
                    }
                    allItems = LOGIC_ITEM.findAllByFilter(mapParam);
                } else {
                    allItems = LOGIC_ITEM.findAll();
                }
                dataReceived = allItems != null && allItems.size() != 0;
                if (dataReceived)
                    outJSON.put("list", new Gson().toJson(allItems));
                break;

            case DELETE:
                dataReceived = LOGIC_ITEM.delete(inputJSON.getInt("itemId"));
                break;

            case CREATE_OR_UPDATE:
                Item item = JSON_PARSER.fromJson(inputJSON.toString(), new TypeReference<Item>() {
                });
                item.setUser(LOGIC_USER.findByID(inputJSON.getInt("userId")));
                item.setCar(LOGIC_CAR.findByID(inputJSON.getInt("carId")));
                dataReceived = LOGIC_ITEM.add(item);
                outJSON.put("itemId", item.getId());
                break;

            case GET_DROPDOWN_LIST:
                Map mapDropDownList = LOGIC_ITEM.getDopdownList();
                dataReceived = mapDropDownList.size() != 0;
                if (dataReceived)
                    outJSON.put("list", new Gson().toJson(mapDropDownList));
                break;

            case GET_BY_ID:
                Item findItem = LOGIC_ITEM.findByID(inputJSON.getInt("itemId"));
                dataReceived = findItem != null;
                if (dataReceived) {
                    outJSON.put("list", new Gson().toJson(findItem));
                }
                break;

            case GET_ALL_FILES:

                List<FileImage> imageList = LOGIC_FILE.findAll(inputJSON.getInt("itemId"));
                dataReceived = imageList.size() != 0;
                if (dataReceived)
                    outJSON.put("list", new Gson().toJson(imageList));
                break;
            default:
        }
        outJSON.put("answer", dataReceived);
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




