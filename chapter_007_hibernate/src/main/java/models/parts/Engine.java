package models.parts;

import lombok.Data;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */

public @Data class Engine{
    private String name;
    private Integer id;

    public Engine(Integer id) {
        this.id = id;
    }

    public Engine() {
    }
}
