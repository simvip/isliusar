package ru.job4j.bank;


/**
 * Created by Ivan Sliusar on 11.09.2017.
 * Red Line Soft corp.
 */
public class Account {
    /**
     * Value money.
     */
    private double value;
    /**
     * number of account user.
     */
    private String requisistes;

    /**
     * Construct.
     *
     * @param value       BigInteger
     * @param requisistes int
     */
    public Account(double value, String requisistes) {
        this.value = value;
        this.requisistes = requisistes;
    }

    /**
     * Getter Value.
     *
     * @return BigInteger
     */
    public double getValue() {
        return value;
    }

    /**
     * Getter Requisistes.
     *
     * @return int
     */
    public String getRequisistes() {
        return requisistes;
    }

    /**
     * Setter value.
     *
     * @param value BigInteger
     */
    public void setValue(double value) {
        this.value = value;
    }
}
