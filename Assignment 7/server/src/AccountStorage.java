/*
 * #TODO
 * In all public methods I send Account object. It is bad solution. Have to redo.
 */

import java.util.ArrayList;

public class AccountStorage extends ArrayList<Account> {

    public AccountStorage() {
        super();
    }

    /*
     * These operational methods do stuff with particular Account objects
     * and they return result.
     * Result is usually account object if all good,
     * or null or false if all bad.
     */
    /**
     * Adds account to storage if there is no account with same accountID
     *
     * @param e Account that we want to add to storage
     * @return True if successfully added. False otherwise
     */
    public Account create(int accountID, double amount) {
        Account result = find(accountID);

        if (result == null) {
            Account newAccount = new Account(accountID, amount);
            super.add(newAccount);

            result = newAccount;
        } else { //if it found something we need to get rid of it and not pass everywhere else
            result = null;
        }

        return result;
    }

    public Account delete(int accountID) {
        Account result = find(accountID);

        if (result != null) {
            result.deleteAccount();
            super.remove(result);
        }

        return result;
    }

    public Account deposit(int accountID, double amount) {
        Account result = find(accountID);

        if (result != null) {
            result.deposit(amount);
        }

        return result;
    }

    /*
     * #TODO REDO THIS BLOODY SHIT
     * Returns array of two objects: boolean and account
     * 
     * if account is null then it does not exist
     * if boolean is true then we do not have enough money
     */
    public Object[] withdraw(int accountID, double amount) {
        Object[] result = new Object[2]; //first item is a boolean, second is an Account obj

        boolean moneyLack = false;
        Account found = find(accountID);

        if (found != null) {
            try {
                found.withdraw(amount);
            } catch (IndexOutOfBoundsException ex) {
                moneyLack = true;
            }
        }

        result[0] = moneyLack;
        result[1] = found;
        return result;
    }

    public Account inquire(int accountID) {
        Account result = find(accountID);

        return result;
    }

    /*
     * Added synchronized modifier so no any different threads 
     * will search in this arraylist at the same time
     */
    /**
     * Returns first found position of account with id accountID
     *
     * @param accountID accountID we want to find
     * @return Account if we found it, or null if not
     */
    private synchronized Account find(int accountID) {
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
