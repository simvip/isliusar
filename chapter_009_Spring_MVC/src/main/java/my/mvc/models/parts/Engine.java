package my.mvc.models.parts;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "engine")
public
@Data
class Engine implements BaseBlock{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Engine(Integer id) {
        this.id = id;
    }

    public Engine() {
    }
}
