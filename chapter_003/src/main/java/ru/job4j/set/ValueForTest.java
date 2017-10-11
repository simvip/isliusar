package ru.job4j.set;

/**
 * Created by Ivan Sliusar on 06.10.2017.
 * Red Line Soft corp.
 */
public class ValueForTest {
    /**
     * Hesh.
     */
    private final int hash;

    /**
     * Construct.
     * @param hash int
     */
    public ValueForTest(int hash) {
        this.hash = hash;
    }

    /**
     * Equals.
     * @param o Object.
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValueForTest that = (ValueForTest) o;

        return hash == that.hash;

    }

    /**
     * Hash code override.
     * @return int.
     */
    @Override
    public int hashCode() {
        return hash;
    }
}
