package my.jpa.service;

import my.jpa.models.Item;
import my.jpa.models.parts.BaseBlock;
import my.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public ItemRepository itemRepo;
    @Autowired
    public CarRepository carRepo;
    @Autowired
    public EngineRepository engineRepo;
    @Autowired
    public GearBoxRepository gearRepo;
    @Autowired
    public TransmissionRepository transmissionRepo;

    private ValidateItem() {}

    private boolean itemExistInBase(int id) {
        return itemRepo.findById(id).get() != null;
    }

    public boolean add(Item item) {
        itemRepo.save(item);
        return itemExistInBase(item.getId());
    }

    public boolean update(Item item) {
        itemRepo.save(item);
        return itemExistInBase(item.getId());
    }

    public boolean delete(int id) {
        Item deleteItem = itemRepo.findById(Integer.valueOf(id)).get();
        if (deleteItem != null)
            itemRepo.delete(deleteItem);
        return !itemExistInBase(id);
    }

    public List<Item> findAllByFilter(Map<String, String> parameters) {

        if (parameters.containsKey("sDate")) {
            java.sql.Timestamp sDate = new Timestamp(Long.parseLong(parameters.get("sDate")));
            java.sql.Timestamp eDate = new Timestamp(Long.parseLong(parameters.get("eDate")));
            return itemRepo.findByCreatedBetween(sDate,eDate);
        }

        if (parameters.containsKey("withPhoto")){
            if (Integer.valueOf(parameters.get("withPhoto"))==1)
                return itemRepo.findByCoverPathIsNotNull();
            else
                return itemRepo.findByCoverPathIsNull();
        }

        if (parameters.containsKey("carId"))
            itemRepo.findByCarId(Integer.valueOf(parameters.get("carId")));

        return null;
    }

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        itemRepo.findAll().forEach(item -> items.add(item));
        return items;
    }

    public Item findByID(int id) {
        return itemRepo.findById(id).get();
    }

    public Map<String, List<BaseBlock>> getDopdownList() {
        Map<String, List<BaseBlock>> listMap = new HashMap<>();
        listMap.put("car",
                findAllInSetRepository(carRepo));
        listMap.put("engine",
                findAllInSetRepository(engineRepo));
        listMap.put("gearbox",
                findAllInSetRepository(gearRepo));
        listMap.put("transmission",
                findAllInSetRepository(transmissionRepo));
        return listMap;
    }
    private List<BaseBlock>findAllInSetRepository(CrudRepository repository){
        List<BaseBlock> blockList = new ArrayList<>();
        repository.findAll().forEach(value -> blockList.add((BaseBlock)value));
        return blockList;
    }
}
