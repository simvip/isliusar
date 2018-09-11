package my.jpa.repository;

import my.jpa.models.Car;
import my.jpa.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item,Integer> {
    List<Item> findByCreatedBetween(Timestamp start, Timestamp end);
    List<Item> findByCoverPathIsNull();
    List<Item> findByCoverPathIsNotNull();
    List<Item> findAllByCar(Car car);
}
