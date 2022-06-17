import java.util.Scanner;

public class Exercise19 {
    public static void interleave(String string1, String string2)   {
        String shortestStringBetween1And2 = "";
        String interleavedString = "";

        if (string1.length() < string2.length()) {
            shortestStringBetween1And2 = string1;
        } else {
            shortestStringBetween1And2 = string2;
        }

        for(int i=0; i < shortestStringBetween1And2.length(); i++) {
            if (i < string1.length()) {
                interleavedString = interleavedString + string1.substring(i, i + 1);
            }
            if (i < string2.length()) {
                interleavedString = interleavedString + string2.substring(i, i + 1);
            }
        }
        if (shortestStringBetween1And2.length() < string1.length()) {
            interleavedString = interleavedString + string1.substring(shortestStringBetween1And2.length());
        } else if (shortestStringBetween1And2.length() < string2.length()) {
            interleavedString = interleavedString + string2.substring(shortestStringBetween1And2.length());
        }
        System.out.println(interleavedString);
    }
    public static void main(String[] args) {
        // INTERLEAVE
        Scanner console = new Scanner(System.in);

        System.out.print("First string: ");
        String first = console.nextLine();

        System.out.print("Second string: ");
        String second = console.nextLine();

        interleave(first, second);
    }
}
// 1. Write a loop to interleave two strings to form a new string.

// To interleave, during each loop take one character from the first string and add it to the result
// and take one character from the second string and add it to the result.
// If there are no more characters available, don't add characters.
// 2. Print the result.

// Examples
// "abc", "123" -> "a1b2c3"
// "cat", "dog" -> "cdaotg"
// "wonder", "o" -> "woonder"
// "B", "igstar" -> "Bigstar"
// "", "huh?" -> "huh?"
// "wha?", "" -> "wha?"