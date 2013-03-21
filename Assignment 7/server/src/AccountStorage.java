/*
 * AccountStorage.java
 * Assignement 7 - Bank system. Server side.
 * 
 * CS152    Nikita Volodin (127196)
 * 
 * This class represents storage of accounts of the system.
 */

import java.util.ArrayList;

public class AccountStorage extends ArrayList<Account> {

    public AccountStorage() {
        super();
    }

    /**
     * Creates new account in the storage
     *
     * @param accountID ID of new account
     * @param amount starting balance
     * @return Starting balance of this account, or null if we already have this
     * account ID
     */
    public synchronized Double create(int accountID, double amount) {
        Double newBalance = null;

        if (find(accountID) == null) { //if we did not find anything with this ID
            Account newAccount = new Account(accountID, amount);
            super.add(newAccount);

            // if we created account then we will save money it has
            newBalance = newAccount.getBalance();
        }

        return newBalance;
    }

    /**
     * Deletes account from the storage
     *
     * @param accountID ID of account we want to delete
     * @return Balance before deletion, or null if we do not have this account
     * ID
     */
    public synchronized Double delete(int accountID) {
        Double deletingBalance = null;

        Account deletingAccount = find(accountID);
        if (deletingAccount != null) { //if we found anything, then it is the account we need to delete
            deletingBalance = deletingAccount.getBalance();
            super.remove(deletingAccount);
        }

        return deletingBalance;
    }

    /**
     * Deposits to account
     *
     * @param accountID ID of account
     * @param amount value to add
     * @return new amount on account, or null if we do not have this account ID
     */
    public Double deposit(int accountID, double amount) {
        Double depositedBalance = null;

        Account depositedAccount = find(accountID);
        if (depositedAccount != null) {
            depositedBalance = depositedAccount.deposit(amount);
        }

        return depositedBalance;
    }

    /**
     * Withdraws from account
     *
     * @param accountID ID of account
     * @param amount amount we want to withdraw
     * @return new amount on the balance, "-1" if we do not have enough money,
     * or null if we do not have this account ID
     */
    public Double withdraw(int accountID, double amount) {
        Double withdrawingBalance = null;

        Account withdrawingAccount = find(accountID);
        if (withdrawingAccount != null) {
            withdrawingBalance = withdrawingAccount.withdraw(amount);
        }

        return withdrawingBalance;
    }

    /**
     * Inquires account
     *
     * @param accountID ID of account
     * @return account balance, or null if we do not have this account ID
     */
    public Double inquire(int accountID) {
        Double inquiringBalance = null;

        Account inquiredAccount = find(accountID);
        if (inquiredAccount != null) {
            inquiringBalance = inquiredAccount.getBalance();
        }

        return inquiringBalance;
    }

    /**
     * Returns first found position of account with id accountID
     *
     * @param accountID accountID we want to find
     * @return Account if we found it, or null if not
     */
    private Account find(int accountID) {
        Account result = null;

        int i = 0;
        while (i < this.size() && accountID != super.get(i).getAccountID()) {
            i++;
        }

        if (i < this.size()) {
            result = this.get(i);
        }

        return result;
    }
}
