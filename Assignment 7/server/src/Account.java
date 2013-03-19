
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Account.java     Nikita Volodin
 * CS152            Assignment 7
 * 
 * Class represents account in the bank
 */
public class Account {

    final private String PATH = "./users/";
    private File outFile = null;
    private FileWriter out = null;
    private int accountID = 0;
    private double money = -1;

    public Account(int account, double startingDeposit) {
        accountID = account;
        money = startingDeposit;

        try {
            createFile();
        } catch (IOException ex) {
            System.out.println(getTime() + "\tCould not create file for account #" + accountID);
            System.err.println(ex);
        }
    }

    private String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        
        return sdf.format(cal.getTime());
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
     * "-1" means that account has insufficient funds
     *
     * @param amount amount to withdraw
     * @return new amount of money on account
     */
    public synchronized double withdraw(double amount) {
        if (amount <= money) {
            try {
                saveToFile("WITHDRAW", amount);
                money -= amount;
            } catch (IOException ex) {
                System.err.println(ex);
            }

            return money;
        } else {
            return -1;
        }
    }

    /**
     * Deletes account
     * 
     * @return Amount of money before deleting
     */
    public synchronized double deleteAccount() {
        outFile.delete();
        
        return money;
    }

    private void createFile() throws IOException {
        outFile = new File(PATH + accountID);
        outFile.delete();           //delete previous file and create new
        outFile.createNewFile();    //each time when we create new instance
        out = new FileWriter(outFile, true);

        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (out != null) {
            out.write("Account ID: " + accountID + "\n\n");
            out.write(getTime() + "\tStarting balance: " + fmt.format(money) + "\n");
        }

        out.close();
    }

    private void saveToFile(String type, double amount) throws IOException {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        out = new FileWriter(outFile, true);

        if (out != null) {
            switch (type) {
                case "DEPOSIT":
                    out.write(getTime() + "\tDEPOSIT:\t" + fmt.format(amount) + "\n");
                    break;

                case "WITHDRAW":
                    out.write(getTime() + "\tWITHDRAW:\t" + fmt.format(amount) + "\n");
                    break;
            }
        }

        out.close();
    }
}
