//*******************************************************************
//  MagazineRack.java       Author: Lewis/Loftus
//
//  Driver to exercise the MagazineList collection.
//*******************************************************************

public class MagazineRack {
    //----------------------------------------------------------------
    //  Creates a MagazineList object, adds several magazines to the
    //  list, then prints it.
    //----------------------------------------------------------------

    public static void main(String[] args) {
        MagazineList rack = new MagazineList();

        Magazine aMagazine = new Magazine("New York Times");
        Magazine bMagazine = new Magazine("Time");
        Magazine cMagazine = new Magazine("Woodworking Today");
        Magazine dMagazine = new Magazine("Communications of the ACM");
        Magazine eMagazine = new Magazine("House and Garden");
        Magazine fMagazine = new Magazine("GQ");

        rack.insert(eMagazine);
        rack.insert(bMagazine);
        rack.insert(dMagazine);
        rack.insert(aMagazine);
        rack.insert(cMagazine);
        rack.insert(fMagazine);
        rack.insert(aMagazine);
        rack.insert(eMagazine);

        rack.delete(aMagazine);
        rack.delete(aMagazine);
        rack.delete(dMagazine);

        System.out.println(rack);
    }
}
