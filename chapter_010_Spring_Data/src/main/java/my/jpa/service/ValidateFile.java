package my.jpa.service;

import my.jpa.models.FileImage;
import my.jpa.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
@Service
public class ValidateFile{
    @Autowired
    private FileRepository REPO;

    private ValidateFile() {
    }

    public void add(FileImage fileImage) {
        REPO.save(fileImage);
    }

    public void update(FileImage fileImage) {
        REPO.save(fileImage);
    }

    public void delete(FileImage fileImage) {
        REPO.delete(fileImage);
    }

    public List<FileImage> findAll(int id) {
        List<FileImage> images = new ArrayList<>();
        REPO.findAllByItemId(Integer.valueOf(id))
                .forEach(fileImage -> images.add(fileImage));
        return images;
    }

    public FileImage findByID(int id) {
        return null;
    }
}
