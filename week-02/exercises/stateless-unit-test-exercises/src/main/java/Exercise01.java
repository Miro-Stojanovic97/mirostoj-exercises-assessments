public class Exercise01 {

    // INTEGER MATH

    // The following methods reimplement math operations.
    // add == +, subtract == -, multiply == *, divide == /
    // Parameters are operands that should be applied in order.
    // For example: a + b, not b + a.

    // [DONE] 1. Open Exercise01Test in src/test/java and run all tests.
    // [DONE] 2. Complete the add and subtract methods and make all tests pass.

    static int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    static int subtract(int a, int b) {
        int diff = a - b;
        return diff;
    }

    // [DONE] 3. Add tests for multiply and divide in Exercise01Test.
    // Provide at least 6 test cases.
    // [DONE] 4. Run all tests.
    // [DONE] 5. Complete the multiply and divide methods and make all tests pass.

    static int multiply(int a, int b) {
        int prod = a * b;
        return prod;
    }

    static int divide(int a, int b) {
        int div = a / b;
        return div;
    }
}
