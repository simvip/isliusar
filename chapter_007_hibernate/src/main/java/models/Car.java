package models;

import lombok.Data;
import models.parts.Engine;
import models.parts.GearBox;
import models.parts.Transmission;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "car")
public @Data class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Engine engine;

    @ManyToOne(cascade = CascadeType.ALL)
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.ALL)
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
