package ru.job4j.professions;

/**
 * Class Profession.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Profession {
    /**
     * nameProfession.
     */
    private String nameProfession;
    /**
     * paymentForHour.
     */
    private int paymentForHour;

    /**
     * Сonstructor.
     *
     * @param paymentForHour args.
     * @param name           args.
     */
    public Profession(String name, int paymentForHour) {
        this.nameProfession = name;
        this.paymentForHour = paymentForHour;
    }

    /**
     * getNameProfession.
     * @return Sring
     */
    public String getNameProfession() {
        return nameProfession;
    }

    /**
     * getPaymentForHour.
     * @return int
     */
    public int getPaymentForHour() {
        return paymentForHour;
    }
}
