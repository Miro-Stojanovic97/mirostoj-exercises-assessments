package PangramWarmup.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PangramTest {
    @Test
    static boolean isPangram(String abcdefghijklmnopqrstuvwxyz){
        boolean expected = true;
        boolean actual = PangramTest.isPangram("abcdefghijklmnopqrstuvwxyz");
        assertEquals(expected, actual);
        return expected;
    }
}
