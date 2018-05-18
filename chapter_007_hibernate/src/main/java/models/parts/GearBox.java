package models.parts;

import lombok.Data;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
public @Data class GearBox{
    private String name;
    private Integer id;

    public GearBox(Integer id) {
        this.id = id;
    }

    public GearBox() {

    }
}
