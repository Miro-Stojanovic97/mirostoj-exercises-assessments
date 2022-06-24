//Warmup

import java.util.Scanner;

public class WarmupMiroKevin {
    public static void main(String[] args) {
        int j = 0;
        boolean validWord = true;
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a word that's a palindrome:   ");
        while (j < 10) { //so that we can guess 10 words quickly
            String word = console.nextLine();
            //method for palindrom
            palindromeMaker(word, validWord);
            j = j + 1;
        }
    }

    static void palindromeMaker(String word, boolean validWord) {
        int lastIndex = word.length() - 1;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(lastIndex - i)) {
                validWord = true;
                if (lastIndex - i <= i) {
                    break;
                }
            } else {
                validWord = false;
                break;
            }
        }

        if (validWord == true) {
            System.out.println("This is a palindrome");
        } else {
            System.out.println("This is not a palindrome");
        }
    }
}
