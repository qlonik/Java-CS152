/* Lab 2 Inheritance research.
 * 
 * We create abstract classes of coin and monetary coin and creating different 
 * inherited classes of different coins : brazilian, chinese, cuban, uk, polish.
 */

public class CoinSaver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create array of 3 new brazilian coins
        BrazilianCoin[] brazilianCoins = {
            new BrazilianCoin(1), new BrazilianCoin(0.25), new BrazilianCoin(0.5)
        };
        //create array of 3 new chinese coins
        ChineseCoin[] chineseCoins = {
            new ChineseCoin(1), new ChineseCoin(0.5), new ChineseCoin(0.1)
        };
        //create array of 3 new cuban coins
        CubanCoin[] cubanCoins = {
            new CubanCoin(3), new CubanCoin(1), new CubanCoin(0.05)
        };
        //create array of 3 new uk coins
        UKCoin[] ukCoins = {
            new UKCoin(1), new UKCoin(2), new UKCoin(0.1)
        };
        //create array of 3 new polish coins
        PolishCoin[] polishCoins = {
            new PolishCoin(5), new PolishCoin(0.2), new PolishCoin(1)
        };

        //sum and print values of coins of different units
        double sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += brazilianCoins[i].getValue();
        }
        System.out.println("You have " + sum + " " + brazilianCoins[0].getUnit()
                + " of brazilian money");

        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += chineseCoins[i].getValue();
        }
        System.out.println("You have " + sum + " " + chineseCoins[0].getUnit()
                + " of chinese money");

        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += cubanCoins[i].getValue();
        }
        System.out.println("You have " + sum + " " + cubanCoins[0].getUnit()
                + " of cuban money");

        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += ukCoins[i].getValue();
        }
        System.out.println("You have " + sum + " " + ukCoins[0].getUnit()
                + " of uk money");

        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += polishCoins[i].getValue();
        }
        System.out.println("You have " + sum + " " + polishCoins[0].getUnit()
                + " of polish money");
        System.out.println();

        //creating array of 15 monetary coins and saving inside all coins from 
        //previous 5 arrays
        MonetaryCoin[] monetaryCoins = new MonetaryCoin[15];

        for (int i = 0; i < 3; i++) {
            monetaryCoins[i + 0 * 3] = brazilianCoins[i];
            monetaryCoins[i + 1 * 3] = chineseCoins[i];
            monetaryCoins[i + 2 * 3] = cubanCoins[i];
            monetaryCoins[i + 3 * 3] = ukCoins[i];
            monetaryCoins[i + 4 * 3] = polishCoins[i];
        }

        //printing all of these coins using inherited toString method
        for (int i = 0; i < 15; i++) {
            System.out.println(monetaryCoins[i]);
        }

        System.out.println();

        //creating a new array of 15 coins and save all coins inside
        Coin[] coins = new Coin[15];
        for (int i = 0; i < 15; i++) {
            coins[i] = monetaryCoins[i];
        }

        //priniting all of them
        for (int i = 0; i < 15; i++) {
            System.out.println(coins[i]);
        }

        //flipping all coins and counting number of heads of these coins
        sum = 0;
        for (int i = 0; i < 15; i++) {
            coins[i].flip();
            if (coins[i].isHeads()) {
                sum++;
            }
        }

        System.out.println("Heads: " + sum + " Tails: " + (15 - sum));
    }
}
