package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class DoctorTest.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *21.06.2017
 */
public class DoctorTest {
    /**
     * checkup.
     */
    @Test
    public void whenCheckupForOldHumanThenGet10000() {
        Person client = new Person("Вениамин", "Горшков", 98, 9, 2);
        Person doctor = new Person("Григорий", "Окочурка", 20, 100, 99);
        Doctor terapevt = new Doctor(100, doctor);
        terapevt.checkup(client);
        int result = terapevt.getOrder();
        int expected = 10000;
        assertThat(result, is(expected));
    }

    /**
     * get order.
     */
    @Test
    public void whenGetOrderForYangHumanThenGetTen() {
        Person client = new Person("Вениамин", "Горшков", 20, 90, 2);
        Person doctor = new Person("Григорий", "Окочурка", 20, 100, 99);
        Doctor terapevt = new Doctor(10, doctor);
        terapevt.checkup(client);
        int result = terapevt.getOrder();
        int expected = 10;
        assertThat(result, is(expected));
    }

}