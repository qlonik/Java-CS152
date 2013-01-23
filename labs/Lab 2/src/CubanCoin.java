
/*
 * Implementation of cuban coin as monetary coin
 */
public class CubanCoin extends MonetaryCoin {

    public CubanCoin() {
        unit = "peso";
    }

    public CubanCoin(double value) {
        unit = "peso";
        this.value = value;
    }
}
