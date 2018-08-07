package my.mvc.logic;

import my.mvc.models.Car;
import my.mvc.persistent.PostgreCarStore;
import my.mvc.persistent.Store;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
public class ValidateCar {
    private static final ValidateCar INSTANCE = new ValidateCar();
    private static final Store STORE = PostgreCarStore.getInstance();

    public static ValidateCar getInstance(){
        return INSTANCE;
    }

    private ValidateCar() {}

    public void add(Car car) {
        STORE.add(car);
    }

    public void update(Car car) {
        STORE.update(car);
    }

    public void delete(Car car) {
        STORE.delete(car);
    }

    public Car findByID(int id) {
        return (Car) STORE.findById(id);
    }
}
