
/*
 * Implementation of coin with value (monetary coin)
 * It has instance var of unit name and value,
 * accessors for name and value and mutator for value,
 * and it has toString method
 */
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
