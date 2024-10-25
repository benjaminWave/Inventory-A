package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Cloth;

public class JsonTest {
    protected void testCloth(String color, String type, int id, Cloth cloth) {
        assertEquals(color, cloth.getColor());
        assertEquals(type, cloth.getClothType());
        assertEquals(id, cloth.getId());
    }
}
