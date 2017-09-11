package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Sliusar on 11.09.2017.
 * Red Line Soft corp.
 */
public class BigBlackBank {
    /**
     * Scroll of users.
     */
    Map<User, List<Account>> users;

    /**
     * Construct.
     */
    public BigBlackBank() {
        this.users = new HashMap<>();
    }

    /**
     * Add user.
     *
     * @param user User.
     */
    public void addUser(User user) {
        this.users.put(user, new ArrayList<>());
    }

    /**
     * Delete User.
     *
     * @param user User
     */
    public void deleteUser(User user) {
        if (this.users.containsKey(user)) {
            this.users.remove(user);
        }
    }

    /**
     * Add account to user.
     *
     * @param user    User
     * @param account Account
     */
    public void addAccountToUser(User user, Account account) {
        if (this.users.containsKey(user)) {
            this.users.get(user).add(account);
        }
    }

    /**
     * Delete account from user.
     *
     * @param user    user.
     * @param account account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        if (this.users.containsKey(user)) {
            this.users.get(user).remove(account);
        }
    }

    /**
     * Getter users account.
     *
     * @param user User.
     * @return List
     */
    public List<Account> getUserAccounts(User user) {
        if (this.users.containsKey(user)) {
            return this.users.get(user);
        }
        return null;
    }

    /**
     * Transfer money.
     *
     * @param srcUser    User
     * @param srcAccount Account
     * @param dstUser    User
     * @param dstAccount Account
     * @param amount     double
     * @return boolean
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {

        boolean statusTransaction = false;

        if (this.users.containsKey(srcUser) && this.users.get(srcUser).contains(srcAccount)) {
            if (srcAccount.getValue() >= amount) {
                if (this.users.containsKey(dstUser) && this.users.get(dstUser).contains(dstAccount)) {
                    dstAccount.setValue(dstAccount.getValue() + amount);
                    statusTransaction = true;
                }
            }
        }
        return statusTransaction;
    }

}
