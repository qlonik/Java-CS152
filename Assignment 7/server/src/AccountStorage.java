/*
 * #TODO
 * In all public methods I send Account object. It is bad solution. Have to redo.
 */

import java.util.ArrayList;

public class AccountStorage extends ArrayList<Account> {

    public AccountStorage() {
        super();
    }

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

    public synchronized Double delete(int accountID) {
        Double deletingBalance = null;

        Account deletingAccount = find(accountID);
        if (deletingAccount != null) { //if we found anything, then it is the account we need to delete
            deletingBalance = deletingAccount.deleteAccount();
            super.remove(deletingAccount);
        }

        return deletingBalance;
    }

    public Double deposit(int accountID, double amount) {
        Double depositedBalance = null;

        Account depositedAccount = find(accountID);
        if (depositedAccount != null) {
            depositedBalance = depositedAccount.deposit(amount);
        }

        return depositedBalance;
    }

    public Double withdraw(int accountID, double amount) {
        Double withdrawingBalance = null;

        Account withdrawingAccount = find(accountID);
        if (withdrawingAccount != null) {
            withdrawingBalance = withdrawingAccount.withdraw(amount);
        }

        return withdrawingBalance;
    }

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
