package my.jpa.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import my.jpa.models.FileImage;
import my.jpa.models.Item;
import my.jpa.service.ValidateFile;
import my.jpa.service.ValidateItem;
import my.jpa.utils.Crud;
import my.jpa.utils.ItemsInputParameters;
import my.jpa.utils.JsonParser;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * Created by Ivan Sliusar on 24.04.2018.
 * Red Line Soft corp.
 */
@RestController
public class ItemServlet {
    @Autowired
    private ValidateItem LOGIC_ITEM;
    @Autowired
    private ValidateFile LOGIC_FILE;

    private JsonParser JSON_PARSER = JsonParser.getInstance();
    private static final Logger logger = Logger.getLogger(ItemServlet.class);
    private Map<Crud, Function<ItemsInputParameters, String>> dispatch = new HashMap<>();

    public ItemServlet() {
        this.dispatch.put(
                Crud.FIND_ALL,
                findAll()
        );
        this.dispatch.put(
                Crud.DELETE,
                delete()
        );
        this.dispatch.put(
                Crud.CREATE_OR_UPDATE,
                createOrUpdate()
        );
        this.dispatch.put(
                Crud.GET_DROPDOWN_LIST,
                getDorpdownList()
        );
        this.dispatch.put(
                Crud.GET_BY_ID,
                getById()
        );
        this.dispatch.put(
                Crud.GET_ALL_FILES,
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
            dataReceived = LOGIC_ITEM.delete(parameters.getId());
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
            dataReceived = LOGIC_ITEM.add(item);
            outJSON.put("itemId", item.getId());
            outJSON.put("answer", dataReceived);
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
            outJSON.put("answer", dataReceived);
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> getById() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            Item findItem = LOGIC_ITEM.findByID(parameters.getId());
            dataReceived = findItem != null;
            if (dataReceived) {
                outJSON.put("list", new Gson().toJson(findItem));
            }
            outJSON.put("answer", dataReceived);
            return outJSON.toString();
        };
    }

    private Function<ItemsInputParameters, String> getAllFiles() {
        return parameters -> {
            Boolean dataReceived = false;
            JSONObject outJSON = new JSONObject();
            List<FileImage> imageList = LOGIC_FILE.findAll(parameters.getId());
            dataReceived = imageList.size() != 0;
            if (dataReceived)
                outJSON.put("list", new Gson().toJson(imageList));
            outJSON.put("answer", dataReceived);
            return outJSON.toString();
        };
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    @ResponseBody
    protected String doPost(@RequestBody ItemsInputParameters parameters) throws ServletException, IOException {
        return handleRequest(parameters);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        return new RedirectView("index.html");
    }
}




