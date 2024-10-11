package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;
public class TestSalesRanking {
    SalesRanking testRanking;

    List<Cloth> clothes;
    @BeforeEach
    public void runBefore(){
        testRanking = new SalesRanking();
        clothes = new ArrayList<>();
        clothes.add(new Cloth("Blue","Shirt",1234));
        clothes.add(new Cloth("Yellow","Shirt",1753));
        clothes.add(new Cloth("Black","Trousers",4888));
    }
    @Test
    public void testConstructor(){
        assertEquals(0,(testRanking.getRanking()).size());
    }
    @Test
    public void testUpdateIfInList(){
        testRanking.update(clothes.get(0));
        testRanking.update(clothes.get(0));
        assertEquals(2,testRanking.getRanking().get(0).getPurchaseCount());
        
    }
    @Test
    public void testUpdateIfNotInList(){
        testRanking.update(clothes.get(0));
        assertEquals(1234,testRanking.getRanking().get(0).getId());
        assertEquals(1,testRanking.getRanking().get(0).getPurchaseCount());
        testRanking.update(clothes.get(2));
        assertEquals(4888,testRanking.getRanking().get(1).getId());
        assertEquals(1,testRanking.getRanking().get(1).getPurchaseCount());
    }
    @Test
    public void testOrganize(){
        testRanking.update(clothes.get(0));
        testRanking.update(clothes.get(1));
        testRanking.update(clothes.get(1));
        testRanking.update(clothes.get(1));
        testRanking.update(clothes.get(2));
        testRanking.update(clothes.get(2));
        testRanking.organize();
        assertTrue(testRanking.getRanking().get(0).getPurchaseCount()>testRanking.getRanking().get(1).getPurchaseCount());
        assertTrue(testRanking.getRanking().get(1).getPurchaseCount()>testRanking.getRanking().get(2).getPurchaseCount());
    }
}

