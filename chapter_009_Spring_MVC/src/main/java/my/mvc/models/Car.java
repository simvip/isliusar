package my.mvc.models;

import lombok.Data;
import my.mvc.models.parts.Engine;
import my.mvc.models.parts.GearBox;
import my.mvc.models.parts.Transmission;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "Car")
public @Data class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_engine")
    private Engine engine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_transmission")
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_gearbox")
    private GearBox gearbox;

    public Car(String name, Engine engine, Transmission transmission, GearBox gearbox) {
        this.name = name;
        this.engine = engine;
        this.transmission = transmission;
        this.gearbox = gearbox;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }
}
