package my.jpa.service;

import my.jpa.models.Car;
import my.jpa.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
@Service
public class ValidateCar {
    @Autowired
    private CarRepository repo;

    private ValidateCar() {}

    public void add(Car car) {
        repo.save(car);
    }

    public void update(Car car) {
        repo.save(car);
    }

    public void delete(Car car) {
        repo.delete(car);
    }

    public Car findByID(int id) {
        return repo.findById(id).get();
    }
}
