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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "userId")
    private User user;

    @Column(name = "description")
    private String desc;

    @Column(name = "coverpath")
    private String coverPath;

    @Column(name = "create_date")
    private Timestamp created;

    @Column(name = "done")
    private Boolean done;

}