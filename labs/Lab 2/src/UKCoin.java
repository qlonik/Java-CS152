
/*
 * Implementation of uk coin as monetary coin
 */
public class UKCoin extends MonetaryCoin {

    public UKCoin() {
        unit = "pound";
    }

    public UKCoin(double value) {
        unit = "pound";
        this.value = value;
    }
}
