package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TeacherTest.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 21.06.2017
 */
public class TeacherTest {
    /**
     * checkOfKnowledge.
     */
    @Test
    public void whenEducationUnderTenThen1000() {
        Person client = new Person("Вениамин", "Горшков", 98, 9, 2);
        Person miha = new Person("Михаил", "Самсонов", 54, 50, 99);
        Teacher instructor = new Teacher(100, miha);
        instructor.checkOfKnowledge(client);
        int result = instructor.getOrder();
        int expected = 1000;
        assertThat(result, is(expected));
    }

    /**
     * getOrder.
     */
    @Test
    public void getOrderwhenEducationMoThen11Under50Then5000() {
        Person client = new Person("Вениамин", "Горшков", 98, 9, 49);
        Person miha = new Person("Михаил", "Самсонов", 54, 50, 99);
        Teacher instructor = new Teacher(100, miha);
        instructor.checkOfKnowledge(client);
        int result = instructor.getOrder();
        int expected = 5000;
        assertThat(result, is(expected));
    }

}