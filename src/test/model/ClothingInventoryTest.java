package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClothingInventoryTest {
    ClothingInventory testInventory;
    Cloth testCloth;
    Cloth testCloth2;
    Cloth testCloth3;
    @BeforeEach
    public void runBefore(){
        testInventory = new ClothingInventory();
        testCloth = new Cloth("Blue","Shirt",1234);
        testCloth2 = new Cloth("Yellow","Shirt",1753);
        testCloth3 = new Cloth("Black","Trousers",4888);
    }
    @Test
    public void testConstructor(){
        assertEquals(0,testInventory.getInventoryList().size());
        assertEquals(0,testInventory.getRequestSize());
    }
    @Test
    public void testAddCloth(){
        testInventory.addCloth(testCloth);
        assertEquals(1234,testInventory.getInventoryList().get(0).getId());
        assertEquals(1,testInventory.getInventoryList().size());
        testInventory.addCloth(testCloth);
        assertEquals(1234,testInventory.getInventoryList().get(1).getId());
        assertEquals(2,testInventory.getInventoryList().size());
        testInventory.addCloth(testCloth2);
        assertEquals(1753,testInventory.getInventoryList().get(2).getId());
        assertEquals(3,testInventory.getInventoryList().size());
    }
    @Test
    public void testRemoveClothesPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);
        assertTrue(testInventory.removeCloth(testCloth.getId()));
        assertEquals(2,testInventory.getInventoryList().size());
        assertTrue(testInventory.removeCloth(testCloth.getId()));
        assertEquals(1,testInventory.getInventoryList().size());
        assertEquals(1753,testInventory.getInventoryList().get(0).getId());
        assertTrue(testInventory.removeCloth(testCloth2.getId()));
        assertEquals(0,testInventory.getInventoryList().size());
    }
    @Test
    public void testRemoveClothesNotPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);
        assertFalse(testInventory.removeCloth(testCloth3.getId()));
        assertEquals(3,testInventory.getInventoryList().size());
    }
    @Test
    public void testBuyItemPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);
        assertTrue(testInventory.buyItem(testCloth.getId()));
        assertEquals(2,testInventory.getInventoryList().size());
        assertTrue(testInventory.buyItem(testCloth.getId()));
        assertEquals(1,testInventory.getInventoryList().size());
        assertEquals(1,testInventory.getRanking().getRanking().size());
        assertEquals(1753,testInventory.getInventoryList().get(0).getId());
        assertTrue(testInventory.buyItem(testCloth2.getId()));
        assertEquals(0,testInventory.getInventoryList().size());
        assertEquals(2,testInventory.getRanking().getRanking().size());
    }
    @Test
    public void testBuyItemNotPresent(){
        assertFalse(testInventory.buyItem(testCloth.getId()));
        assertEquals(0,testInventory.getInventoryList().size());
        assertEquals(0,testInventory.getRanking().getRanking().size());
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);
        assertFalse(testInventory.buyItem(testCloth3.getId()));
        assertEquals(3,testInventory.getInventoryList().size());
        assertEquals(0,testInventory.getRanking().getRanking().size());
    }
    @Test
    public void testRequestItemPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);
        assertTrue(testInventory.requestItem(testCloth.getId()));
        assertEquals(0,testInventory.getRequestSize());
        testInventory.requestItem(testCloth3.getId());
        assertTrue(testInventory.requestItem(testCloth2.getId()));
        assertEquals(1,testInventory.getRequestSize()); 

    }
    @Test
    public void testRequestItemPresentAfterRemovedDuplicate(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);  
        testInventory.removeCloth(testCloth.getId());
        assertTrue(testInventory.requestItem(testCloth.getId()));
        assertEquals(0,testInventory.getRequestSize());
    }
    @Test
    public void testRequestItemNotPresent(){
        assertFalse(testInventory.requestItem(testCloth.getId()));
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        assertFalse(testInventory.requestItem(testCloth3.getId()));
        assertEquals(1,testInventory.getRequestSize());
        assertFalse(testInventory.requestItem(testCloth3.getId()));
        assertEquals(1,testInventory.getRequestSize());
        assertFalse(testInventory.requestItem(testCloth2.getId()));
        assertEquals(2,testInventory.getRequestSize());

    }
    @Test
    public void testSearchListForClothesPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);  
        assertEquals(0,testInventory.searchListForClothes(testCloth.getId()));
        assertEquals(2,testInventory.searchListForClothes(testCloth2.getId()));
        testInventory.addCloth(testCloth3);
        assertEquals(3,testInventory.searchListForClothes(testCloth3.getId()));
        testInventory.addCloth(testCloth3);
        assertEquals(3,testInventory.searchListForClothes(testCloth3.getId()));
    }
    @Test
    public void testSearchListForClothesNotPresent(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);  
        assertEquals(-1,testInventory.searchListForClothes(testCloth3.getId()));
    }
    @Test
    public void testViewClothes(){
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth);
        testInventory.addCloth(testCloth2);  
        String message = "Current Clothes: \n-Blue Shirt - 1234 \n-Blue Shirt - 1234 \n-Yellow Shirt -1753";
        assertEquals(message, testInventory.viewClothes());
    }
    @Test
    public void testViewClothesEmptyList(){
        assertEquals("There are no clothes added!", testInventory.viewClothes());
    }
   

}
