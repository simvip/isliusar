package ru.job4j.iterator;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan Sliusar on 15.09.2017.
 * Red Line Soft corp.
 */
public class ConverterTest {
    /**
     * Test converter.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(10);
        Iterator<Iterator<Integer>> it = Arrays.asList(
                list.iterator(),
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.hasNext();
        convert.next();
        convert.hasNext();
        convert.next();
        convert.hasNext();
        convert.next();
        convert.hasNext();
        int result = convert.next();
        assertThat(result, is(1));
    }

}