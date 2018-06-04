package models.parts;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "transmission")
public @Data class Transmission{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Transmission(Integer id) {
        this.id = id;
    }

    public Transmission() {
    }
}
