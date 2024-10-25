package persistence;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import model.Cloth;
import model.ClothingInventory;

public class JsonWriterTest extends JsonTest {
    ClothingInventory inventory;
    Cloth testCloth;
    Cloth testCloth2;
    Cloth testCloth3;

    @BeforeEach
    public void runBefore() {
        inventory = new ClothingInventory();
        testCloth = new Cloth("Blue", "Shirt", 1234);
        testCloth2 = new Cloth("Yellow", "Shirt", 1753);
        testCloth3 = new Cloth("Black", "Trousers", 4888);
    }

    @Test
    public void testWriteInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");

        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriteEmptyInventory() {
        try {
            runBefore();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inventory);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            ClothingInventory inventoryRead = reader.read();
            assertEquals(inventoryRead.getInventoryList().size(), 0);
            assertEquals(inventoryRead.getRequestSize(), 0);
            assertEquals(inventoryRead.getRanking().getRanking().size(), 0);
            assertEquals("There are no clothes added!", inventory.viewClothes());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriteTypicalInventory() {
        try {
            runBefore();
            testScenario();
            JsonWriter writer = new JsonWriter("./data/testWriterTypicalInventory.json");
            writer.open();
            writer.write(inventory);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterTypicalInventory.json");
            ClothingInventory inventoryRead = reader.read();
            assertEquals(inventoryRead.getInventoryList().size(), 2);
            assertEquals(inventoryRead.getRequestSize(), 2);
            assertEquals(inventoryRead.getRanking().getRanking().size(), 2);
            List<Cloth> clothes = inventory.getInventoryList();
            testCloth(testCloth.getColor(), testCloth.getClothType(), testCloth.getId(), clothes.get(0));
            testCloth(testCloth2.getColor(), testCloth2.getClothType(), testCloth2.getId(), clothes.get(1));
            String message = "Current Clothes: \n-Blue Shirt - 1234 \n-Yellow Shirt - 1753";
            assertEquals(message, inventory.viewClothes());
            message = "Sales Ranking: \n-Yellow Shirt (3) - 1753 \n-Black Trousers (3) - 4888";
            assertEquals(message, inventory.displayRanking());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void testScenario() {
        inventory.addCloth(testCloth);
        inventory.addCloth(testCloth2);
        inventory.addCloth(testCloth2);
        inventory.addCloth(testCloth3);
        inventory.addCloth(testCloth3);
        inventory.addCloth(testCloth3);
        inventory.addCloth(testCloth);
        inventory.buyItem(testCloth3.getId());
        inventory.buyItem(testCloth2.getId());
        inventory.addCloth(testCloth2);
        inventory.addCloth(testCloth2);
        inventory.buyItem(testCloth2.getId());
        inventory.buyItem(testCloth2.getId());
        inventory.buyItem(testCloth3.getId());
        inventory.buyItem(testCloth3.getId());
        inventory.removeCloth(testCloth.getId());
        inventory.requestItem(2000);
        assertFalse(inventory.requestItem(testCloth.getId()));

        inventory.requestItem(3000);
    }
}
