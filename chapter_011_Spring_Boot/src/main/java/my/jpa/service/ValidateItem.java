package my.jpa.service;

import my.jpa.models.Item;
import my.jpa.models.parts.BaseBlock;
import my.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
@Service
public class ValidateItem {
    @Autowired
    public ItemRepository ITEM_REPO;
    @Autowired
    public CarRepository CAR_REPO;
    @Autowired
    public EngineRepository ENGINE_REPO;
    @Autowired
    public GearBoxRepository GEAR_REPO;
    @Autowired
    public TransmissionRepository TRANSMISSION_REPO;

    private ValidateItem() {}

    private boolean itemExistInBase(int id) {
        return ITEM_REPO.findById(id).get() != null;
    }

    public boolean add(Item item) {
        ITEM_REPO.save(item);
        return itemExistInBase(item.getId());
    }

    public boolean update(Item item) {
        ITEM_REPO.save(item);
        return itemExistInBase(item.getId());
    }

    public boolean delete(int id) {
        Item deleteItem = ITEM_REPO.findById(Integer.valueOf(id)).get();
        if (deleteItem != null)
            ITEM_REPO.delete(deleteItem);
        return !itemExistInBase(id);
    }

    public List<Item> findAllByFilter(Map<String, String> parameters) {
//        //typify the parameters
//        Map<String, Object> typifyMap = new HashMap<>();
//        if (parameters.containsKey("sDate")) {
//            java.sql.Timestamp sDate = new Timestamp(Long.parseLong(parameters.get("sDate")));
//            typifyMap.put("sDate", sDate);
//            java.sql.Timestamp eDate = new Timestamp(Long.parseLong(parameters.get("eDate")));
//            typifyMap.put("eDate", eDate);
//        }
//
//        if (parameters.containsKey("withPhoto"))
//            typifyMap.put("withPhoto", Integer.valueOf(parameters.get("withPhoto"))==1);
//
//        if (parameters.containsKey("carId"))
//            typifyMap.put("carId", Integer.valueOf(parameters.get("carId")));
//
//        return ITEM_REPO.findAllByParam(typifyMap);
        return null;
    }

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        ITEM_REPO.findAll().forEach(item -> items.add(item));
        return items;
    }

    public Item findByID(int id) {
        return ITEM_REPO.findById(id).get();
    }

    public Map<String, List<BaseBlock>> getDopdownList() {
        Map<String, List<BaseBlock>> listMap = new HashMap<>();
        listMap.put("car",
                findAllInSetRepository(CAR_REPO));
        listMap.put("engine",
                findAllInSetRepository(ENGINE_REPO));
        listMap.put("gearbox",
                findAllInSetRepository(GEAR_REPO));
        listMap.put("transmission",
                findAllInSetRepository(TRANSMISSION_REPO));
        return listMap;
    }
    private List<BaseBlock>findAllInSetRepository(CrudRepository repository){
        List<BaseBlock> blockList = new ArrayList<>();
        repository.findAll().forEach(value -> blockList.add((BaseBlock)value));
        return blockList;
    }
}
