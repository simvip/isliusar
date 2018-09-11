package my.jpa.models.parts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 16.05.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "gearbox")
@AllArgsConstructor
@Data
@Builder
public class GearBox implements BaseBlock{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public GearBox(Integer id) {
        this.id = id;
    }

    public GearBox() {}
}
