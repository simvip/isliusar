package my.jpa.repository;

import my.jpa.models.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface ItemRepository extends CrudRepository<Item,Integer> {
}
