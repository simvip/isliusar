package ru.job4j.strategy;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class PaintTest.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 29.06.2017
 */
public class PaintTest {

    /**
     * Test Draw Square.
     */
    @Test
    public void whenPaintSqure4x4ThenPrintSqure4X4() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint(new Square()).draw();
        assertThat(
                out.toString(),
                is(
                        String.format(
                                "* * * *%s* * * *%<s* * * *%<s* * * *",
                                System.getProperty("line.separator")
                        )
                )
        );
    }
    /**
     * Test Triangle.
     */
    @Test
    public void whenPaintTriangleThenPrintTriangleHigt3Width5() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint(new Triangle()).draw();
        assertThat(
                out.toString(),
                is(
                        String.format(
                                "  x  %s ххх %<sxxxxx",
                                System.getProperty("line.separator")
                        )
                )
        );
    }
}