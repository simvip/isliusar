package my.jpa.repository;

import my.jpa.models.parts.Transmission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface TransmissionRepository extends CrudRepository<Transmission,Integer>{
}
