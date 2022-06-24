import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise01Test {

    @Test
    void add() {
        assertEquals(2, Exercise01.add(1, 1));
        assertEquals(0, Exercise01.add(112, -112));
        assertEquals(-256, Exercise01.add(-206, -50));
        assertEquals(256, Exercise01.add(150, 106));
        assertEquals(17, Exercise01.add(10, 7));
        assertEquals(-5, Exercise01.add(300, -305));
    }

    @Test
    void subtract() {
        assertEquals(0, Exercise01.subtract(10, 10));
        assertEquals(5, Exercise01.subtract(10, 5));
        assertEquals(-15, Exercise01.subtract(10, 25));
        assertEquals(100000, Exercise01.subtract(100001, 1));
        assertEquals(-200, Exercise01.subtract(50, 250));
        assertEquals(13, Exercise01.subtract(40, 27));
    }

    @Test
    void multiply() {
        assertEquals(1, Exercise01.multiply(1, 1));
        assertEquals(0, Exercise01.multiply(1, 0));
        assertEquals(4, Exercise01.multiply(2, 2));
        assertEquals(-4, Exercise01.multiply(2, -2));
        assertEquals(8, Exercise01.multiply(-4, -2));
        assertEquals(1000, Exercise01.multiply(100, 10));
    }

    @Test
    void divide() {
        assertEquals(1, Exercise01.divide(1, 1));
        assertEquals(0, Exercise01.divide(0, 1));
        assertEquals(4, Exercise01.divide(8, 2));
        assertEquals(-4, Exercise01.divide(8, -2));
        assertEquals(5, Exercise01.divide(-10, -2));
        assertEquals(100, Exercise01.divide(1000, 10));
    }

}