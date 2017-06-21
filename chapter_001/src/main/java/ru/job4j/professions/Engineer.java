package ru.job4j.professions;

/**
 * Class Engineer.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Engineer extends Profession {
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
    public Engineer(int paymentForHour, Person person) {
        super("Engineer", paymentForHour);
        this.person = person;
    }

    /**
     * checkup.
     *
     * @param person args.
     */
    public void doSomething(Person person) {
        int age = person.getAge();
        if (age <= 5) {
            doOregami(person);
        } else if (age > 11 && age <= 40) {
            fixBike(person);
        } else if (age > 51 && age <= 90) {
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
    private void doOregami(Person person) {
        System.out.printf("Смотри %s какую тебя дядя поделку скрафтил из бумаги", person.getName());
        this.order += 1 * getPaymentForHour();
    }

    /**
     * givePilzz.
     * @param person person
     */
    private void fixBike(Person person) {
        System.out.printf("Ухайдохали %s Вы совой велосипед, час 2 с ним провозился", person.getName());
        this.order += 3 * getPaymentForHour();
    }

    /**
     * doNothing.
     * @param person person
     */
    private void doNothing(Person person) {
        System.out.printf("Это %s %s не мой профиль", person.getName(), person.getSoName(), person.getAge());
        this.order += 0 * getPaymentForHour();
    }
}


