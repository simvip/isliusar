package my.jpa.repository;

import my.jpa.models.parts.GearBox;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface GearBoxRepository extends CrudRepository<GearBox,Integer> {
}
