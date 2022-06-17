import java.util.Random;

public class Exercise11 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // 1. Count the number of positive elements in `values`.
        int positiveCount = 0;
        for(int i=0; i < values.length; i++) {
            if (values[i] > 0) {
                positiveCount++;
            }
        }
        System.out.printf("There are %d numbers", positiveCount);
        positiveCount = 0;
        // 2. Create a new int[] to hold the positive elements.
        int[] positives = new int[positiveCount];
        for(int j = 0; j < values.length; j++) {
            if(values[j] > 0) {
                positives[positiveCount] = values[j];
                positiveCount++;

            }
        }
        // (We must count first to know the capacity to allocate.)
        // 3. Loop through `values` a second time. Add positive elements to the new array.
        for (int i = 0; i < positives.length; i++) {
            System.out.println(positives[i]);
        }
        // 4. Confirm the positive array is properly populated either by debugging or printing its elements.

    }
    public static int[] returnPositiveNumbers(int[] values){
        int[] positives = new int[values.length];
        new int[positiveCount];
        int positiveCount = 0;
        for(int j = 0; j < values.length; j++) {
            if(values[j] > 0) {
                positives[positiveCount] = values[j];
                positiveCount++;

            }
        }
        return;
    }
    public static int[] makeRandomArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }

}
