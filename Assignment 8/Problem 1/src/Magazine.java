//********************************************************************
//  Magazine.java       Author: Lewis/Loftus
//
//  Represents a single magazine.
//********************************************************************

public class Magazine implements Comparable<Magazine> {

    private String title;

    //-----------------------------------------------------------------
    //  Sets up the new magazine with its title.
    //-----------------------------------------------------------------
    public Magazine(String newTitle) {
        title = newTitle;
    }

    //-----------------------------------------------------------------
    //  Returns this magazine as a string.
    //-----------------------------------------------------------------
    public String toString() {
        return title;
    }

    //added by pp 13.2 and as8
    //implementation of comparable interface
    public int compareTo(Magazine obj) {
        return title.compareToIgnoreCase(obj.title);
    }
}
