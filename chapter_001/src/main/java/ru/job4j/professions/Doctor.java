package ru.job4j.professions;

/**
 * Class Doctor.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Doctor extends Profession {
    /**
     * person.
     */
    private Person person;
    /**
     * order.
     */
    private int order;

    /**
     * Сonstructor.
     *
     * @param paymentForHour args.
     * @param person         args.
     */
    public Doctor(int paymentForHour, Person person) {
        super("Doctor", paymentForHour);
        this.person = person;
    }

    /**
     * checkup.
     *
     * @param person args.
     */
    public void checkup(Person person) {
        int health = person.getHealthy();
        if (health <= 10) {
            haveOperation(person);
        } else if (health > 11 && health <= 50) {
            givePilzz(person);
        } else if (health > 51 && health <= 90) {
            doNothing(person);
        }
    }

    /**
     * getter order.
     *
     * @return order int
     */
    public int getOrder() {
        return order;
    }

    /**
     * haveOperation.
     * @param person person
     */
    private void haveOperation(Person person) {
        System.out.printf("О батенька %s да вы совсем плохи, строчно нужно Вас почикать", person.getName());
        this.order += 100 * getPaymentForHour();
    }

    /**
     * givePilzz.
     * @param person person
     */
    private void givePilzz(Person person) {
        System.out.printf("Удивительно %s как Вы в таком состоянии еще землю топчите, а ну ка скушайте таблеточку", person.getName());
        this.order += 10 * getPaymentForHour();
    }

    /**
     * doNothing.
     * @param person person
     */
    private void doNothing(Person person) {
        System.out.printf("Да %s %s Вы здоровы как бык, для вашего возроста %d это очень хороший показатель", person.getName(), person.getSoName(), person.getAge());
        this.order += 1 * getPaymentForHour();
    }
}
