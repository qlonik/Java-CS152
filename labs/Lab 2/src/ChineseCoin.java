
public class ChineseCoin extends MonetaryCoin {

    public ChineseCoin() {
        unit = "yuan";
    }

    public ChineseCoin(double value) {
        unit = "yuan";
        this.value = value;
    }
}
