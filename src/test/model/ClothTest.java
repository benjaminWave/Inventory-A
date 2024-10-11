package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClothTest {
    Cloth testCloth;

    @BeforeEach
    public void runBefore() {
        testCloth = new Cloth("Blue", "Shirt", 1234);
    }

    @Test
    public void testConstructor() {
        assertEquals("Blue", testCloth.getColor());
        assertEquals("Shirt", testCloth.getClothType());
        assertEquals(1234, testCloth.getId());
        assertEquals(0, testCloth.getPurchaseCount());
    }

    @Test
    public void testBuyOnce() {
        testCloth.buy();
        assertEquals(1, testCloth.getPurchaseCount());
    }

    @Test
    public void testBuyMultipleTimes() {
        testCloth.buy();
        testCloth.buy();
        testCloth.buy();
        assertEquals(3, testCloth.getPurchaseCount());
    }
}
