package models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Ivan Sliusar on 21.04.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "items")
public
@Data
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "decs")
    private String decs;

    @Column(name = "create_date")
    private Timestamp created;

    @Column(name = "done")
    private Boolean done;

}