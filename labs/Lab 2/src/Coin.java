
/*
 * Implementation of coin that can be flipped and does not have value
 */
public abstract class Coin {

    protected final int HEADS = 0;
    protected final int TAILS = 1;
    protected int face;

    public Coin() {
        flip();
    }

    public void flip() {
        face = (int) (Math.random() * 2);
    }

    public boolean isHeads() {
        return (face == HEADS);
    }

    @Override
    public String toString() {
        String faceName;

        if (face == HEADS) {
            faceName = "Heads";
        } else {
            faceName = "Tails";
        }

        return faceName;
    }
}