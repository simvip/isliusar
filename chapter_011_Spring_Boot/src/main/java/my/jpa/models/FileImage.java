package my.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
@Entity
@Table(name = "filespath")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;
}
