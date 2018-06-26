package logic;

import models.Item;
import models.parts.BaseBlock;
import persistent.PostgreCarStore;
import persistent.PostgreItemStore;
import persistent.Store;
import persistent.parts.PostgreEngineStore;
import persistent.parts.PostgreGearBoxStore;
import persistent.parts.PostgreTransmissionStore;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
public class ValidateItem {

    private static final ValidateItem INSTANCE = new ValidateItem();

    private static final Store STORE = PostgreItemStore.getInstance();

    public static ValidateItem getInstance() {
        return INSTANCE;
    }

    private ValidateItem() {
    }

    private boolean itemExistInBase(int id) {
        return STORE.findById(id) != null;
    }

    public boolean add(Item item) {
        if (item.getId() == null)
            STORE.add(item);
        else
            STORE.update(item);
        return itemExistInBase(item.getId());
    }


    public boolean update(Item item) {
        STORE.update(item);
        return itemExistInBase(item.getId());
    }

    public boolean delete(int id) {
        Item deleteItem = (Item) STORE.findById(id);
        if (deleteItem != null)
            STORE.delete(deleteItem);
        return !itemExistInBase(id);
    }

    public List<Item> findAllByFilter(Map<String, String> parameters) {
        //typify the parameters
        Map<String, Object> typifyMap = new HashMap<>();
        if (parameters.containsKey("sDate")) {

            java.sql.Timestamp sDate = new Timestamp(Long.parseLong(parameters.get("sDate")));
            typifyMap.put("sDate", sDate);

            java.sql.Timestamp eDate = new Timestamp(Long.parseLong(parameters.get("eDate")));
            typifyMap.put("eDate", eDate);
        }
        if (parameters.containsKey("withPhoto"))
            typifyMap.put("withPhoto", Integer.valueOf(parameters.get("withPhoto"))==1);
        if (parameters.containsKey("carId"))
            typifyMap.put("carId", Integer.valueOf(parameters.get("carId")));

        return STORE.findAllByParam(typifyMap);
    }

    public List<Item> findAll() {
        return STORE.findAll();
    }

    public Item findByID(int id) {
        return (Item) STORE.findById(id);
    }

    public Map<String, List<BaseBlock>> getDopdownList() {

        Map<String, List<BaseBlock>> listMap = new HashMap<>();

        listMap.put("engine", PostgreEngineStore.getInstance().findAll());
        listMap.put("gearbox", PostgreGearBoxStore.getInstance().findAll());
        listMap.put("transmission", PostgreTransmissionStore.getInstance().findAll());

        listMap.put("car", PostgreCarStore.getInstance().findAll());

        return listMap;
    }
}
