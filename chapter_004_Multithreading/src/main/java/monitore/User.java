package monitore;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by Ivan Sliusar on 31.10.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class User {
    /**
     * Id user.
     */
    private int id;
    /**
     * Amount.
     */
    private int amount;

    /**
     * Construct.
     *
     * @param id     int
     * @param amount int
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Getter.
     *
     * @return int
     */
    public synchronized int getAmount() {
        return amount;
    }

    /**
     * Add cash to the amount.
     *
     * @param amount int
     */
    public synchronized void setAmount(int amount) {
        this.amount = this.amount + amount;
    }

    /**
     * Override equals.
     *
     * @param o Object
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

        User user = (User) o;

        return id == user.id;

    }

    /**
     * Getter id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Override hashcode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return id;
    }
}
