
public class MySequence {

    public static int getValue(int limit) {
        if (limit == 0 || limit == 1 || limit == 2) {
            return 1;
        } else {
            return getValue(limit - 1) + getValue(limit - 2) + getValue(limit - 3);
        }
    }
}