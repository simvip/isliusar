package ru.job4j.strategy;

/**
 * Class Paint.
 *
 * @author i.sliusar
 * @version $Id$
 * @since 0.1
 * 29.06.2017
 */
public class Paint {
    /**
     * Shape.
     */
    Shape shape;

    /**
     * construct.
     * @param shape
     */
    public Paint(Shape shape) {
        this.shape = shape;
    }

    /**
     * draw shape.
     */
    public void draw() {
        System.out.print(shape.pic());
    }
}
