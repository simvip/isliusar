package my.jpa.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.util.Map;


/**
 * Created by Ivan Sliusar on 01.08.2018.
 * Red Line Soft corp.
 */
public
@Getter
@Setter
class ItemsInputParameters {
    private static final Logger logger = Logger.getLogger(ItemsInputParameters.class);
    /**
     * Command what we do.
     */
    private String command;

    /**
     * Parameters for db request.
     */
    private String queryParam;

    private int id;
    private String desc;
    private String coverPath;
    private String done;
    private String created;
    private Map<String,Integer> car;
    private Map<String,Integer> user;

    public JSONArray getQueryParamInJsonArray() {
        return new JSONArray(this.queryParam);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(this);
        logger.info(json);
        return json;
    }
}
