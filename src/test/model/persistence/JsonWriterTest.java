package model.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Cloth;
import model.ClothingInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

public class JsonWriterTest extends JsonTest {
    ClothingInventory inventory ;
    Cloth testCloth;
    Cloth testCloth2;
    Cloth testCloth3;

    @BeforeEach
    public void runBefore(){
        inventory = new ClothingInventory();
        testCloth = new Cloth("Blue", "Shirt", 1234);
        testCloth2 = new Cloth("Yellow", "Shirt", 1753);
        testCloth3 = new Cloth("Black", "Trousers", 4888);
    }
    @Test
    public void testWriteInvalidFile(){
        try{
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");

        }catch(IOException e){
            //pass
        }
    }
    @Test
    public void testWriteEmptyInventory(){
        try{
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inventory);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            ClothingInventory inventoryRead = reader.read();
            assertEquals(inventoryRead .getInventoryList().size(), 0);
            assertEquals(inventoryRead .getRequestSize(), 0);
            assertEquals(inventoryRead .getRanking().getRanking().size(), 0);


        }catch(IOException e){
            fail("Exception should not have been thrown");
        }
    }
    @Test
    public void TestWriteTypicalInventory(){
        try{
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            inventory.addCloth(testCloth);
            inventory.addCloth(testCloth2);
            inventory.addCloth(testCloth2);
            inventory.addCloth(testCloth3);
            inventory.addCloth(testCloth3);
            inventory.addCloth(testCloth3);
            inventory.buyItem(testCloth3.getId());
            inventory.buyItem(testCloth2.getId());
            inventory.buyItem(testCloth3.getId());
            inventory.requestItem(2000);
            inventory.requestItem(3000);
            writer.write(inventory);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            ClothingInventory inventoryRead = reader.read();
            assertEquals(inventoryRead .getInventoryList().size(), 3);
            assertEquals(inventoryRead .getRequestSize(), 2);
            assertEquals(inventoryRead .getRanking().getRanking().size(), 2);
            List<Cloth> clothes = inventory.getInventoryList();
            testCloth(testCloth.getColor(), testCloth.getPurchaseCount(), testCloth.getClothType(), testCloth.getId(), clothes.get(0));
            testCloth(testCloth2.getColor(), testCloth2.getPurchaseCount(), testCloth2.getClothType(), testCloth2.getId(), clothes.get(1));
            testCloth(testCloth3.getColor(), testCloth3.getPurchaseCount(), testCloth3.getClothType(), testCloth3.getId(), clothes.get(2));

        }catch(IOException e){
            fail("Exception should not have been thrown");
        }
    }
}
