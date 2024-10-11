package model;
import java.util.List;
import java.util.ArrayList;
// Represents the ranking of a list  of items, itemList, based on sales
public class SalesRanking {
    private List<Cloth> itemList; //List of clothes
    /*
     * EFFECTS: itemList is instantiated as an empty list;
     */
    public SalesRanking(){
        //stub
    }
    /*
     * MODIFIES: this
     * EFFECTS: Adds cloth to the itemList if an instance of an item with them same ID does not already exist in the list;
     *          it modifies the purchaseCount of the existing cloth item
     */
    public void update(Cloth cloth){

    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts itemList from the cloth with the highest purchase count to the lowest
    */
    public void organize(){
        //Stub
    }

    
    public List<Cloth> getRanking(){
        return null;
    }
}
