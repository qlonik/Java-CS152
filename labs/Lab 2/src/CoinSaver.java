
public class CoinSaver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BrazilianCoin[] brazilianCoins = {
            new BrazilianCoin(1), new BrazilianCoin(0.25), new BrazilianCoin(0.5)
        };
        ChineseCoin[] chineseCoins = {
            new ChineseCoin(1), new ChineseCoin(0.5), new ChineseCoin(0.1)
        };
        CubanCoin[] cubanCoins = {
            new CubanCoin(3), new CubanCoin(1), new CubanCoin(0.05)
        };
        UKCoin[] ukCoins = {
            new UKCoin(1), new UKCoin(2), new UKCoin(0.1)
        };
        PolishCoin[] polishCoins = {
            new PolishCoin(5), new PolishCoin(0.2), new PolishCoin(1)
        };

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

        MonetaryCoin[] monetaryCoins = new MonetaryCoin[15];

        for (int i = 0; i < 3; i++) {
            monetaryCoins[i + 0 * 3] = brazilianCoins[i];
            monetaryCoins[i + 1 * 3] = chineseCoins[i];
            monetaryCoins[i + 2 * 3] = cubanCoins[i];
            monetaryCoins[i + 3 * 3] = ukCoins[i];
            monetaryCoins[i + 4 * 3] = polishCoins[i];
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(monetaryCoins[i]);
        }

        System.out.println();

        Coin[] coins = new Coin[15];
        for (int i = 0; i < 15; i++) {
            coins[i] = monetaryCoins[i];
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(coins[i]);
        }

        sum = 0;
        for (int i = 0; i < 15; i++) {
            coins[i].flip();
            if (coins[i].isHeads()) {
                sum ++;
            }
        }
        
        System.out.println("Heads: " + sum + " Tails: " + (15 - sum));
    }
}
