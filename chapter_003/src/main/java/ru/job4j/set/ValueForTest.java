package ru.job4j.set;

/**
 * Created by Ivan Sliusar on 06.10.2017.
 * Red Line Soft corp.
 */
public class ValueForTest {
    private final int hesh;

    public ValueForTest(int hesh) {
        this.hesh = hesh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueForTest that = (ValueForTest) o;

        return hesh == that.hesh;

    }

    @Override
    public int hashCode() {
        return hesh;
    }
}
