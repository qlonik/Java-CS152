
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
    private String name = null;
    private int accountID = 0;
    private long money = 0;

    public Account(String name, int account, int startingDeposit) {
        this.name = name;
        accountID = account;
        money = startingDeposit;

        try {
            createFile();
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        }
    }

    public String getName() {
        return name;
    }

    public int getAccountID() {
        return accountID;
    }

    public long getMoney() {
        return money;
    }

    /**
     * Put money on account
     *
     * @param amount amount to add
     * @return new amount of money on account
     */
    public long deposit(int amount) {
        try {
            saveToFile("DEPOSIT", amount);
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        }
        
        money += amount;
        return money;
    }

    /**
     * Withdraw money from account
     *
     * @param amount amount to withdraw
     * @return new amount of money on account
     */
    public long withdraw(int amount) {
        if (amount <= money) {
            try {
                saveToFile("WITHDRAW", amount);
            } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
            }
            
            money -= amount;
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

        if (!outFile.exists()) {
            outFile.createNewFile();
        }

        out = new FileWriter(outFile, true);

        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (out != null) {
            out.write("Name: " + name + "\t\t\tAccount ID: " + accountID + "\n\n");
            out.write("Starting deposit: " + fmt.format(money) + "\n");
        }

        out.close();
    }

    private void saveToFile(String type, long amount) throws IOException {
        out = null;
        File outFile = new File(PATH + accountID);

        if (!outFile.exists()) {
            outFile.createNewFile();
        }

        out = new FileWriter(outFile, true);

        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        if (out != null) {
            if (type.equals("DEPOSIT")) {
                out.write("Added " + fmt.format(amount) + "\t\tCurrent state: "
                        + fmt.format(getMoney()) + "\n");
            }
        }

        out.close();
    }
}