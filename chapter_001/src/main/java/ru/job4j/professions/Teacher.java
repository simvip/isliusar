package ru.job4j.professions;

/**
 * Class Teacher.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class Teacher extends Profession {
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
    public Teacher(int paymentForHour, Person person) {
        super("Teacher", paymentForHour);
        this.person = person;
    }

    /**
     * checkup.
     *
     * @param person args.
     */
    public void checkOfKnowledge(Person person) {
        int education = person.getEducation();
        if (education <= 10) {
            giveBasicEducation(person);
        } else if (education > 11 && education <= 50) {
            giveStandartEducation(person);
        } else if (education > 51 && education <= 90) {
            privateConsultation(person);
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
     * giveBasicEducation.
     *
     * @param person person
     */
    private void giveBasicEducation(Person person) {
        System.out.printf("%s Вам нужно начать с азов", person.getName());
        this.order += 10 * getPaymentForHour();
    }

    /**
     * giveStandartEducation.
     *
     * @param person person
     */
    private void giveStandartEducation(Person person) {
        System.out.printf("%s что-то вы уже знаете, но это не достаточно для среднего уровня", person.getName());
        this.order += 50 * getPaymentForHour();
    }

    /**
     * privateConsultation.
     *
     * @param person person
     */
    private void privateConsultation(Person person) {
        this.order += 100 * getPaymentForHour();
        System.out.printf("Да %s %s Вы чертовски умные для вашего возроста %d это очень хороший показатель, но мы его улучшим", person.getName(), person.getSoName(), person.getAge());
    }
}