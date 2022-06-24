

import java.util.Scanner;

public class Scanners {
    public static void main(String[] args) {
        Scanner one = new Scanner("one!two!three!four");
        Scanner two = new Scanner("one!two!three!four");
        two.useDelimiter("!");

        String result;

        System.out.println("one's delimiter: " + one.delimiter());
        while (one.hasNext()) {
            result = one.next();
            System.out.println(result);
        }

        System.out.println("two's delimiter: " + two.delimiter());
        while (two.hasNext()) {
            result = two.next();
            System.out.println(result);
        }
    }
}