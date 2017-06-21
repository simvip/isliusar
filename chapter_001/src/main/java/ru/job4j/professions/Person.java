package ru.job4j.professions;

/**
 * Class Person extends Human.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Person extends Human {
    /**
     * healthy.
     */
    private int healthy;
    /**
     * education.
     */
    private int education;

    /**
     * Сonstructor.
     *
     * @param name   args.
     * @param soName args.
     * @param age    args.
     */
    public Person(String name, String soName, int age) {
        super(name, soName, age);
    }

    /**
     * Сonstructor.
     *
     * @param name       args.
     * @param soName     args.
     * @param age        args.
     * @param healthy    args.
     * @param education  args.
     */
    public Person(String name, String soName, int age, int healthy, int education) {
        super(name, soName, age);
        this.healthy = healthy;
        this.education = education;
    }

    /**
     * getter Healthy.
     *
     * @return Healthy
     */
    public int getHealthy() {
        return healthy;
    }

    /**
     * getter Education.
     *
     * @return Education
     */
    public int getEducation() {
        return education;
    }
}
