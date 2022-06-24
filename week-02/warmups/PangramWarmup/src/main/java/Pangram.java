import java.util.Locale;

public class Pangram {

    public static boolean isPangram(String pangram) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        char[] alphArray = alph.toCharArray();

        pangram = pangram.replaceAll(" ", "");
        pangram = pangram.toLowerCase();

        if (pangram.length() < 26) {
            return false;
        }

        for (int i = 0; i < alphArray.length; i++) {
            if (!pangram.contains(String.valueOf(alphArray[i]))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPangram("abcfghijklmnopqrstuvwxyz"));
    }
}
