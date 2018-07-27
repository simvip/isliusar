package my.mvc.logic;

import my.mvc.models.FileImage;
import my.mvc.persistent.PostgreFileStore;

import java.util.List;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
public class ValidateFile{
    private static final ValidateFile INSTANCE = new ValidateFile();
    private static final PostgreFileStore STORE = PostgreFileStore.getInstance();

    public static ValidateFile getInstance(){
        return INSTANCE;
    }
    private ValidateFile() {
    }


    public void add(FileImage fileImage) {
        STORE.add(fileImage);
    }


    public void update(FileImage fileImage) {
        STORE.update(fileImage);
    }


    public void delete(FileImage fileImage) {
        STORE.delete(fileImage);

    }

    public List<FileImage> findAll(int id) {
        if (id!=0)
            return STORE.findAll(id);
        return null;
    }

    public FileImage findByID(int id) {
        return null;
    }
}
