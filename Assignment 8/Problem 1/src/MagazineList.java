//*******************************************************************
//  MagazineList.java       Author: Lewis/Loftus
//
//  Represents a collection of magazines.
//*******************************************************************

public class MagazineList {

    private MagazineNode list;

    //----------------------------------------------------------------
    //  Sets up an initially empty list of magazines.
    //----------------------------------------------------------------
    public MagazineList() {
        list = null;
    }

    //----------------------------------------------------------------
    //  Creates a new MagazineNode object and adds it to the end of
    //  the linked list.
    //----------------------------------------------------------------
    //replaced by insert method
//    public void add(Magazine mag) {
//        MagazineNode node = new MagazineNode(mag);
//        MagazineNode current;
//
//        if (list == null) {
//            list = node;
//        } else {
//            current = list;
//            while (current.next != null) {
//                current = current.next;
//            }
//            current.next = node;
//        }
//    }
    //----------------------------------------------------------------
    //  Returns this list of magazines as a string.
    //----------------------------------------------------------------
    public String toString() {
        String result = "";

        MagazineNode current = list;

        while (current != null) {
            result += current.magazine + "\n";
            current = current.next;
        }

        return result;
    }

    //*****************************************************************
    //  An inner class that represents a node in the magazine list.
    //  The public variables are accessed by the MagazineList class.
    //*****************************************************************
    private class MagazineNode {

        public Magazine magazine;
        public MagazineNode next;

        //--------------------------------------------------------------
        //  Sets up the node
        //--------------------------------------------------------------
        public MagazineNode(Magazine mag) {
            magazine = mag;
            next = null;
        }
    }

    //added by pp 13.2 and as8
    public void insert(Magazine obj) {
        MagazineNode node = new MagazineNode(obj);
        MagazineNode current, previous;

        if (list == null) {
            list = node;
        } else {
            previous = null;
            current = list;

            //find the position to insert
            while (current.magazine.compareTo(obj) < 0
                    && current.next != null) {
                previous = current;
                current = current.next;
            }

            //inequations without equal allow to skip
            //repeated names
            if (current.magazine.compareTo(obj) > 0) {
                if (previous == null) { //if we are in the beginning of the list
                    node.next = list;
                    list = node;
                } else {
                    node.next = previous.next;
                    previous.next = node;
                }
            } else if (current.magazine.compareTo(obj) < 0) {
                node.next = current.next;
                current.next = node;
            }
        }
    }

    //addd by pp 13.2 and as8
    public boolean delete(Magazine obj) {
        boolean result = false;

        if (list != null) {
            MagazineNode current = list;
            MagazineNode previous = null;

            while (current.magazine.compareTo(obj) != 0
                    && current.next != null) {
                previous = current;
                current = current.next;
            }

            //if magazine we are looking for does not exist
            //we will end up on the last position
            //and if this last position does not match with 
            //the magazine we want to delete, then we are sure that
            //magazine that we want to delete does not exist
            if (current.magazine.compareTo(obj) == 0) {
                result = true;
                if (previous == null) {
                    list = current.next;
                } else {
                    previous.next = current.next;
                }
            }
        }

        return result;
    }
}
