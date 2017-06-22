package ru.job4j.bigtask.model;

/**
 * Class Item.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Item {
    /**
     * id.
     */
    private String id;
    /**
     * name.
     */
    private String name;
    /**
     * description.
     */
    private String description;
    /**
     * create.
     */
    private long create;
    /**
     *
     * Item.
     * @param name String
     * @param description String
     * @param create long
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * setter id.
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getId.
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * getName.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter Description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter Create.
     * @return create
     */
    public long getCreate() {
        return create;
    }
}
