public class Driver { 
	public static void main(String [] args) {
		for (int i = 0; i < 59; i++) {
			System.out.println(i+ "\t"+BlackBox.Sequence(i));
		}
	}
}
