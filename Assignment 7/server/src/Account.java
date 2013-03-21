/*
 * Account.java
 * Assignement 7 - Bank system. Server side.
 * 
 * CS152    Nikita Volodin (127196)
 * 
 * This class represents account in the bank
 */

public class Account {

    private int accountID = 0;
    private double money = -1;

    public Account(int account, double startingDeposit) {
        accountID = account;
        money = startingDeposit;
    }

    /**
     * Returns ID of this account in int form
     *
     * @return account id
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * Returns amount of money on account
     *
     * @return current balance of account
     */
    public synchronized double getBalance() {
        return money;
    }

    /**
     * Put money on account
     *
     * @param amount amount to add
     * @return new amount of money on account
     */
    public synchronized double deposit(double amount) {
        money += amount;

        return money;
    }

    /**
     * Withdraw money from account. "-1" means that account has insufficient
     * funds
     *
     * @param amount amount to withdraw
     * @return new amount of money on account
     */
    public synchronized double withdraw(double amount) {
        if (amount <= money) {
            money -= amount;

            return money;
        } else {
            return -1;
        }
    }
}
