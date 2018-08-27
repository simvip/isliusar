package my.jpa.repository;

import my.jpa.models.FileImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ivan Sliusar on 10.08.2018.
 * Red Line Soft corp.
 */
public interface FileRepository extends CrudRepository<FileImage,Integer> {
    List<FileImage> findAllByItemId(Integer id);
}
