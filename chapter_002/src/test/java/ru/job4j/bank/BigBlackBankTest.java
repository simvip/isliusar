package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 11.09.2017.
 * Red Line Soft corp.
 */
public class BigBlackBankTest {
    /**
     * Test add User.
     */
    @Test
    public void addUser() {
        User user1 = new User("John", "AB011589");
        BigBlackBank bank = new BigBlackBank();
        bank.addUser(user1);
        assertTrue(bank.users.containsKey(user1));
    }

    /**
     * Test delete User.
     */
    @Test
    public void deleteUser() {
        User user1 = new User("John", "AB011589");
        BigBlackBank bank = new BigBlackBank();
        bank.addUser(user1);
        bank.deleteUser(user1);
        assertFalse(bank.users.containsKey(user1));
    }

    /**
     * Test add Account to user.
     */
    @Test
    public void addAccountToUser() {
        User user1 = new User("John", "AB011589");
        Account account = new Account(100.0, "260000546454056");
        BigBlackBank bank = new BigBlackBank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account);
        assertTrue(bank.getUserAccounts(user1).contains(account));
    }

    /**
     * Delete Account from user.
     */
    @Test
    public void deleteAccountFromUser() {
        User user1 = new User("John", "AB011589");
        Account account1 = new Account(100.0, "260000546454056");
        Account account2 = new Account(1000.0, "2604442456454056");
        BigBlackBank bank = new BigBlackBank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user1, account2);
        bank.deleteAccountFromUser(user1, account2);
        assertFalse(bank.getUserAccounts(user1).contains(account2));
    }

    /**
     * Get user accounts.
     */
    @Test
    public void getUserAccounts() {
        User user1 = new User("John", "AB011589");
        Account account1 = new Account(100.0, "260000546454056");
        Account account2 = new Account(1000.0, "2604442456454056");
        BigBlackBank bank = new BigBlackBank();
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user1, account2);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        assertEquals(bank.getUserAccounts(user1), accounts);
    }

    /**
     * Test transfer money.
     */
    @Test
    public void whereTransferMoneyOkWhenTrue() {
        User srcUser = new User("John", "AB011589");
        Account srcAccount = new Account(1000.0, "260000546454056");
        User dstUser = new User("Piter", "AB011589");
        Account dstAccount = new Account(100.0, "2600054124554056");

        BigBlackBank bank = new BigBlackBank();
        bank.addUser(srcUser);
        bank.addUser(dstUser);
        bank.addAccountToUser(srcUser, srcAccount);
        bank.addAccountToUser(dstUser, dstAccount);

        assertTrue(bank.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 500.0));
    }

    /**
     * Test transfer money.
     */
    @Test
    public void whenTransferMoneyALackOfMoneyTheyFalse() {
        User srcUser = new User("John", "AB011589");
        Account srcAccount = new Account(1000.0, "260000546454056");
        User dstUser = new User("Piter", "AB011589");
        Account dstAccount = new Account(100.0, "2600054124554056");

        BigBlackBank bank = new BigBlackBank();
        bank.addUser(srcUser);
        bank.addUser(dstUser);
        bank.addAccountToUser(srcUser, srcAccount);
        bank.addAccountToUser(dstUser, dstAccount);

        assertFalse(bank.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 1500));
    }
}