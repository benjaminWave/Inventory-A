package model.persistence;

import org.junit.Test;

import model.Cloth;
import model.ClothingInventory;
import persistence.JsonReader;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.*;
import java.util.List;
public class JsonReaderTest extends JsonTest {
    @Test
    public void testReadNonExistentFile(){
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try{
            ClothingInventory inventory = reader.read();
             fail("IOException expected");
        }catch(IOException e){
            //pass
        }
    }
    @Test
    public void testReadEmptyInventory(){
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try{
            ClothingInventory inventory = reader.read();
            assertEquals(inventory.getInventoryList().size(), 0);
            assertEquals(inventory.getRequestSize(), 1);
            assertEquals(inventory.getRanking().getRanking().size(), 0);
             
        }catch(IOException e){
            fail("Unable to read from file");
        }
    }
    @Test
    public void testReadTypicalInventory(){
        JsonReader reader = new JsonReader("./data/testReaderTypicalInventory.json"); 
        try{
            ClothingInventory inventory = reader.read();
            assertEquals(inventory.getInventoryList().size(), 3);
            assertEquals(inventory.getRequestSize(), 2);
            assertEquals(inventory.getRanking().getRanking().size(), 3);
            List<Cloth> clothes = inventory.getInventoryList();
            testCloth("Red", 0, "Shirt", 1234, clothes.get(0));
            testCloth("Blue", 0, "Trousers", 4321, clothes.get(1));
            testCloth("Yellow", 1, "Shirt", 8132, clothes.get(2));
        }catch(IOException e){
            fail("Unable to read from file");
        }
    }
}
