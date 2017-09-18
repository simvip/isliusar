package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
abstract class Base {
    /**
     * id.
     */
    private String id;

    /**
     * Getter.
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Setter.
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }
}
