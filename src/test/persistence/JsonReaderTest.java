package persistence;

import model.Cloth;
import model.ClothingInventory;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

public class JsonReaderTest extends JsonTest {
    @Test
    public void testReadNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ClothingInventory inventory = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReadEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            ClothingInventory inventory = reader.read();
            assertEquals(inventory.getInventoryList().size(), 0);
            assertEquals(inventory.getRequestSize(), 1);
            assertEquals(inventory.getRanking().getRanking().size(), 0);
            assertEquals("There are no clothes added!", inventory.viewClothes());
            // assertEquals("No clothes have been bought!", inventory.displayRanking());

        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }

    @Test
    public void testReadTypicalInventory() {
        JsonReader reader = new JsonReader("./data/testReaderTypicalInventory.json");
        try {
            ClothingInventory inventory = reader.read();
            assertEquals(inventory.getInventoryList().size(), 3);
            assertEquals(inventory.getRequestSize(), 2);
            assertEquals(2, inventory.getRanking().getRanking().size());
            List<Cloth> clothes = inventory.getInventoryList();
            testCloth("Red", "Shirt", 1234, clothes.get(0));
            testCloth("Blue", "Trousers", 4321, clothes.get(1));
            testCloth("Yellow", "Shirt", 8132, clothes.get(2));
            String message = "Sales Ranking: \n-Yellow Shirt (2) - 8132 \n-Purple Shirt (1) - 4932";
            assertEquals(message, inventory.displayRanking());
            message = "Sales Ranking: \n-Yellow Shirt (2) - 8132 \n-Purple Shirt (1) - 4932";
            assertEquals(message, inventory.displayRanking());
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }
}
