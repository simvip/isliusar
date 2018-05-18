package models;

import lombok.Data;
import models.parts.Engine;
import models.parts.GearBox;
import models.parts.Transmission;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
public @Data class Car {
    private Integer id;
    private String name;
    private Engine engine;
    private Transmission transmission;
    private GearBox gearbox;

    public Car(String name, Engine engine, Transmission transmission, GearBox gearbox) {
        this.name = name;
        this.engine = engine;
        this.transmission = transmission;
        this.gearbox = gearbox;
    }

    public Car() {
    }
}
