
/*
 * Implementation of brazilian coin as monetary coin
 */
public class BrazilianCoin extends MonetaryCoin {

    public BrazilianCoin() {
        unit = "real";
    }

    public BrazilianCoin(double value) {
        unit = "real";
        this.value = value;
    }
}
