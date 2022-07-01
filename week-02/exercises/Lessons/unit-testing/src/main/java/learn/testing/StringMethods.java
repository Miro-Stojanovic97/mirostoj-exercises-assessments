package learn.testing;

public class StringMethods {
    public static void main(String[] args) {

    }
    //to do actual implementation
    public static int countDigits(String value) {
        if (value == null) { // account for null
            return 0;
        }

        int count = 0;
        for (int index = 0; index < value.length(); index++) {
            if (Character.isDigit(value.charAt(index))) {
                count++;
            }
        }
        return count;
    }
}