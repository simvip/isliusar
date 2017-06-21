package ru.job4j.professions;

/**
 * Class Human.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Human {
    /**
     * name.
     */
    private String name;
    /**
     * soName.
     */
    private String soName;
    /**
     * age.
     */
    private int age;

    /**
     * Сonstructor.
     *
     * @param name   args.
     * @param soName args.
     * @param age    args.
     */
    public Human(String name, String soName, int age) {
        this.name = name;
        this.soName = soName;
        this.age = age;
    }

    /**
     * getName.
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * get soName.
     * @return String
     */
    public String getSoName() {
        return soName;
    }
    /**
     * get age.
     * @return int
     */
    public int getAge() {
        return age;
    }
}
