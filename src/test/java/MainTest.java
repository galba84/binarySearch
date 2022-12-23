import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    
    Integer[] args = {1, 2, 3, 4, 5, 6, 58, 9, 6, 89, 7, 8, 55, 443, 21};
    

    @Test
    void testPositionSuccess() {
        boolean position = Main.getPosition(Arrays.asList(args), 6);
        assertTrue(position);
    }

    @Test
    void testPositionFail() {
        boolean position = Main.getPosition(Arrays.asList(args), 71);
        assertFalse(position);
    }
}