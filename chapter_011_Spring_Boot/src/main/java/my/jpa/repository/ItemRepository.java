package my.jpa.repository;

import my.jpa.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface ItemRepository extends CrudRepository<Item,Integer> {
    List<Item> findByCreatedBetween(Timestamp start, Timestamp end);
    List<Item> findByCoverPathIsNull();
    List<Item> findByCoverPathIsNotNull();
    List<Item> findByCarId(Integer carId);
}
