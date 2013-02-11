
public class Lab5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lab5 lab = new Lab5();

        String s = "Hello, dude. HOW IS IT GOING?";
        int num = lab.vowels(s.toLowerCase());
        System.out.println(num);
        
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        lab.printArray(0, arr);
        
        System.out.println();
        lab.printReverseArray(0, arr);
        
        int number = 71077345;
        int sum = lab.sumOfDigits(number);
        System.out.println(sum);
    }

    public int vowels(String s) {
        if (s.length() == 0) {
            return 0;
        } else {
            int var = 0;
            if ("aeiou".indexOf(s.toLowerCase().charAt(0)) > 0) {
                var = 1;
            }
            return var + vowels(s.substring(1));
        }
    }
    
    public void printArray(int begin, int[] arr) {
        if (begin + 1 == arr.length) {
            System.out.println(arr[begin]);
        } else {
            System.out.println(arr[begin]);
            printArray(begin + 1, arr);
        }
    }
    
    public void printReverseArray(int begin, int[] arr) {
        if (begin + 1 == arr.length) {
            System.out.println(arr[begin]);
        } else {
            printReverseArray(begin + 1, arr);
            System.out.println(arr[begin]);
        }
    }
    
    public int sumOfDigits(int num) {
        if (num / 10 == 0) {
            return num;
        } else {
            return num % 10 + sumOfDigits(num / 10);
        }
    }
}
