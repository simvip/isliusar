package my.mvc.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import my.mvc.logic.ValidateCar;
import my.mvc.logic.ValidateFile;
import my.mvc.logic.ValidateItem;
import my.mvc.logic.ValidateUser;
import my.mvc.models.FileImage;
import my.mvc.models.Item;
import my.mvc.utils.Crud;
import my.mvc.utils.ItemsInputParameters;
import my.mvc.utils.JsonParser;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static my.mvc.utils.Crud.*;


/**
 * Created by Ivan Sliusar on 24.04.2018.
 * Red Line Soft corp.
 */
@RestController
public class ItemServlet {
    /**
     * Instance validate layer.
     */
    private static final Logger logger = Logger.getLogger(ItemServlet.class);
    private static final ValidateItem LOGIC_ITEM = ValidateItem.getInstance();
    private static final ValidateFile LOGIC_FILE = ValidateFile.getInstance();
    private static final ValidateUser LOGIC_USER = ValidateUser.getInstance();
    private static final ValidateCar LOGIC_CAR = ValidateCar.getInstance();
    private static final JsonParser JSON_PARSER = JsonParser.getInstance();
    private static final Map<Crud, Function<ItemsInputParameters, String>> dispatch = new HashMap<>();

    public ItemServlet() {
        dispatch.put(
                FIND_ALL,
                findAll()
        );
        dispatch.put(
                DELETE,
                delete()
        );
        dispatch.put(
                CREATE_OR_UPDATE,
                createOrUpdate()
        );
        dispatch.put(
                GET_DROPDOWN_LIST,
                getDorpdownList()
        );
        dispatch.put(
                GET_BY_ID,
                getById()
        );
        dispatch.put(
                GET_ALL_FILES,
                getAllFiles()
        );
    }

    public String handleRequest(final ItemsInputParameters parameters) {
        Crud command = Crud.valueOf(parameters.getCommand());
        if (this.dispatch.containsKey(command)) {
            return this.dispatch.get(
                    command
            ).apply(parameters);
        } else {
            logger.error("Command not find");
            return "";
        }
    }

    private Function<ItemsInputParameters, String> findAll() {
        return parameters -> {
            logger.info("Find items");
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            List<Item> allItems;
            if (parameters.getQueryParam() != null) {
                Map<String, String> mapParam = new HashMap<>();
                JSONArray jArray = parameters.getQueryParamInJsonArray();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jb = jArray.getJSONObject(i);
                    mapParam.put(jb.getString("name"), jb.getString("value"));
                }
                allItems = LOGIC_ITEM.findAllByFilter(mapParam);
                logger.info("Find items by param. Size of found list " + allItems.size());
            } else {
                allItems = LOGIC_ITEM.findAll();
                logger.info("Find all items. Size of found list " + allItems.size());
            }
            dataReceived = allItems != null && allItems.size() != 0;
            if (dataReceived)
                outJSON.put("list", new Gson().toJson(allItems));
            outJSON.put("answer", dataReceived);
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> delete() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            dataReceived = LOGIC_ITEM.delete(parameters.getItemId());
            outJSON.put("answer", dataReceived);
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> createOrUpdate() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            Item item = JSON_PARSER.fromJson(
                    parameters.toString(),
                    new TypeReference<Item>() {
                    }
            );
            item.setUser(LOGIC_USER.findByID(parameters.getUserId()));
            item.setCar(LOGIC_CAR.findByID(parameters.getCarId()));
            dataReceived = LOGIC_ITEM.add(item);
            outJSON.put("itemId", item.getId());
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> getDorpdownList() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            Map mapDropDownList = LOGIC_ITEM.getDopdownList();
            dataReceived = mapDropDownList.size() != 0;
            if (dataReceived)
                outJSON.put("list", new Gson().toJson(mapDropDownList));
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> getById() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            Item findItem = LOGIC_ITEM.findByID(parameters.getItemId());
            dataReceived = findItem != null;
            if (dataReceived) {
                outJSON.put("list", new Gson().toJson(findItem));
            }
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> getAllFiles() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            List<FileImage> imageList = LOGIC_FILE.findAll(parameters.getItemId());
            dataReceived = imageList.size() != 0;
            if (dataReceived)
                outJSON.put("list", new Gson().toJson(imageList));
            return outJSON.toString();
        };
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    @ResponseBody
    protected String doPost(@RequestBody ItemsInputParameters parameters) throws ServletException, IOException {
        return handleRequest(parameters);
    }
}




