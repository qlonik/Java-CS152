
public abstract class MonetaryCoin extends Coin {

    String unit;
    double value;

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue() + " " + getUnit();
    }
    
    
}
