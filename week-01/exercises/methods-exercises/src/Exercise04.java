public class Exercise04 {

    public static void main(String[] args) {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:

        // 2. Call getFirstVowel at least one more time.
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)
    public static char getFirstVowel(String value) {

        // vowels: a e i o u y

        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == 'a' || value.charAt(i) == 'e' || value.charAt(i) == 'i' ||
                    value.charAt(i) == 'o' || value.charAt(i) == 'u' || value.charAt(i) == 'y') ;
            return value.charAt(i);
        }
        //switch (c)
        return 0;
    }
}
