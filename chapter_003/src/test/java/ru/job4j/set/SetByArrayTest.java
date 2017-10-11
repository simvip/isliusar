package ru.job4j.set;

import org.junit.Test;


/**
 * Created by Ivan Sliusar on 30.09.2017.
 * Red Line Soft corp.
 */
public class SetByArrayTest {
    /**
     * Test add to Set.
     */
    @Test
    public void whenAddToSetValueWithContainedInSetAddDoNothing() {
        SetByArray<ValueForTest> setByArray = new SetByArray<>(100);

        long start = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            setByArray.add(new ValueForTest(i));
        }
        long end = System.nanoTime();

        long beforeModification = end - start;
        System.out.println("Перед модификацией скорость вставки (наносекунд) " + beforeModification);

        SetByArray<ValueForTest> setByArrayindex = new SetByArray<>(50);

        start = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            setByArrayindex.addheshCode(new ValueForTest(i));
        }
        end = System.nanoTime();

        long afterModification = end - start;
        System.out.println("После модификации скорость вставки (наносекунд) " + afterModification);

        System.out.println("После модификации скорость вставки возросла в " + beforeModification / afterModification);

        // SetByArray<String> setArray = new SetByArray<>(100);
//        int maxValueInSet = 0;
//        for (String value : setArray) {
//       //     System.out.println(value);
//            maxValueInSet++;
//        }
//       assertThat(maxValueInSet, is(100));
    }

}