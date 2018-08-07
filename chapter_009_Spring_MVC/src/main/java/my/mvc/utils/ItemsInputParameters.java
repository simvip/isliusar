package my.mvc.utils;


import lombok.Data;
import org.json.JSONArray;


/**
 * Created by Ivan Sliusar on 01.08.2018.
 * Red Line Soft corp.
 */
public @Data class ItemsInputParameters {
    /**
     * Command what we do.
     */
    private String command;

    /**
     * Parameters for db request.
     */
    private String queryParam;

    int itemId;
    int carId;
    int userId;

    public JSONArray getQueryParamInJsonArray(){
        return new JSONArray(this.queryParam);
    }
}
