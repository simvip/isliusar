package my.jpa.service;

import my.jpa.models.Item;
import my.jpa.models.parts.BaseBlock;
import my.jpa.repository.*;
import my.jpa.utils.RequestParameter;
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


    private ValidateItem() {
    }

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

    public List<Item> findAllByFilter(ItemRequestWrapper wrapper) {
        ArrayList<RequestParameter> parameters = wrapper.getParameters();
        if (parameters.size() == 1) {
            RequestParameter parameter = parameters.get(0);
            if (parameter.getKey().equals("withPhoto")) {
                if (Integer.valueOf(parameter.getValue()) == 1)
                    return itemRepo.findByCoverPathIsNotNull();
                else
                    return itemRepo.findByCoverPathIsNull();
            }
            if (parameter.getKey().equals("carId")) {
                Integer carId = Integer.valueOf(parameter.getValue());
                return itemRepo.findAllByCar(carRepo.findById(carId).get());
            }
        } else {
            java.sql.Timestamp eDate = null;
            java.sql.Timestamp sDate = null;
            for (RequestParameter parameter : parameters) {
                if (parameter.getKey().equals("sDate")) {
                    sDate = new Timestamp(Long.parseLong(parameter.getValue()));
                } else if (parameter.getKey().equals("eDate")) {
                    eDate = new Timestamp(Long.parseLong(parameter.getValue()));
                }
            }
            return itemRepo.findByCreatedBetween(sDate, eDate);
        }
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

    private List<BaseBlock> findAllInSetRepository(CrudRepository repository) {
        List<BaseBlock> blockList = new ArrayList<>();
        repository.findAll().forEach(value -> blockList.add((BaseBlock) value));
        return blockList;
    }
}
