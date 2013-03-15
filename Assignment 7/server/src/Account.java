
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

/*
 * Account.java     Nikita Volodin
 * CS152            Assignment 7
 * 
 * Class represents account in the bank
 */
public class Account {

    final private String PATH = "./users/";
    private FileWriter out = null;
    private int accountID = 0;
    private double money = 0;

    public Account(int account, double startingDeposit) {
        accountID = account;
        money = startingDeposit;

        try {
            createFile();
        } catch (IOException ex) {
            System.out.println("Could not create file for account #" + accountID);
            System.err.println(ex);
        }
    }

    public Account(String account, String startingDeposit) {
        this(Integer.parseInt(account), Double.parseDouble(startingDeposit));
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
    public double getBalance() {
        return money;
    }

    /**
     * Put money on account
     *
     * @param amount amount to add
     * @return new amount of money on account
     */
    public double deposit(double amount) {
        try {
            saveToFile("DEPOSIT", amount);
            money += amount;
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return money;
    }

    /**
     * Withdraw money from account
     *
     * @param amount amount to withdraw
     * @return new amount of money on account
     */
    public double withdraw(int amount) {
        if (amount <= money) {
            try {
                saveToFile("WITHDRAW", amount);
                money -= amount;
            } catch (IOException ex) {
                System.err.println(ex);
            }

            return money;
        } else {
            throw new IndexOutOfBoundsException("Not enough money on account");
        }
    }

    public boolean deleteAccount() {
        File outFile = new File(PATH + accountID);
        if (outFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    private void createFile() throws IOException {
        out = null;
        File outFile = new File(PATH + accountID);
        outFile.delete();           //delete previous file and create new
        outFile.createNewFile();    //each time when we create new instance

        out = new FileWriter(outFile, true);

        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (out != null) {
//            out.write("Name: " + name + "\t\t\tAccount ID: " + accountID + "\n\n");
            out.write("Account ID: " + accountID + "\n\n");
            out.write("Starting deposit: " + fmt.format(money) + "\n");
        }

        out.close();
    }

    private void saveToFile(String type, double amount) throws IOException {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (out != null) {
            switch (type) {
                case "DEPOSIT":
                    out.write("DEPOSIT:\t\t " + fmt.format(amount));
                    break;
                case "WITHDRAW":
                    out.write("WITHDRAW\t\t:" + fmt.format(amount));
                    break;
            }
        }

        out.close();
    }
}
