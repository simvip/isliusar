package models.parts;

import lombok.Data;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
public @Data class Transmission{
    private String name;
    private Integer id;

    public Transmission(Integer id) {
        this.id = id;
    }

    public Transmission() {
    }
}
