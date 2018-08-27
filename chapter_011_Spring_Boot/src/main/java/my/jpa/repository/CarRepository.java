package my.jpa.repository;

import my.jpa.models.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface CarRepository extends CrudRepository<Car,Integer> {
}
