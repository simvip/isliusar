package models;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Ivan Sliusar on 21.04.2018.
 * Red Line Soft corp.
 */
public @Data class Item {
    private Integer id;
    private String decs;
    private Timestamp created;
    private Boolean done;

}