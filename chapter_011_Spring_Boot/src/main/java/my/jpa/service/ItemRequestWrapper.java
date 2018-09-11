package my.jpa.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.jpa.models.Item;
import my.jpa.utils.Crud;
import my.jpa.utils.RequestParameter;
import org.json.JSONArray;

import java.util.ArrayList;


/**
 * Created by Ivan Sliusar on 06.09.2018.
 * Red Line Soft corp.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemRequestWrapper {
    public Crud command;
    public ArrayList<RequestParameter> parameters;
    public Item item;

    public JSONArray getQueryParamInJsonArray() {


        return new JSONArray(this.parameters);
    }

}
