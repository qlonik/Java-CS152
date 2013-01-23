
/*
 * Implementation of polish coin as monetary coin
 */
public class PolishCoin extends MonetaryCoin {

    public PolishCoin() {
        unit = "złoty";
    }

    public PolishCoin(double value) {
        unit = "złoty";
        this.value = value;
    }
}
