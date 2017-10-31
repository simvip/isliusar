package monitore;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan Sliusar on 31.10.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class UserStorage {
    /**
     * Storage.
     */
    private Map<Integer, User> storage;

    /**
     * Construct.
     */
    public UserStorage() {
        this.storage = new HashMap<>();
    }

    public synchronized void add(User user) {
        storage.put(user.getId(), user);
    }

    public void update(User user) {
        add(user);
    }

    public synchronized void delete(int id) {
        storage.remove(id);
    }

    /**
     * Transfer.
     *
     * @param fromid int
     * @param toId   int
     * @param amoutn int
     * @return boolean
     */
    public boolean transfer(int fromid, int toId, int amoutn) {
        User userFrom = storage.get(fromid);
        User userTo = storage.get(toId);
        if (userFrom == null && userTo == null) {
            return false;
        }

        if (fromid < toId) {
            synchronized (userFrom) {
                synchronized (userTo) {
                    return transferMoney(userFrom, userTo, amoutn);
                }
            }
        } else {
            synchronized (userTo) {
                synchronized (userFrom) {
                    return transferMoney(userFrom, userTo, amoutn);
                }
            }
        }

    }

    /**
     * Transfer money.
     *
     * @param formUser User
     * @param toUser   User
     * @param amount   int
     * @return boolean
     */
    private boolean transferMoney(User formUser, User toUser, int amount) {
        if (formUser.getAmount() < amount) {
            return false;
        }
        formUser.setAmount(-amount);
        toUser.setAmount(amount);
        return true;
    }
}
