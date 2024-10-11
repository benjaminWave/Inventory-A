package model;
import java.util.List;
import java.util.ArrayList;
// Represents an inventory of clothes in possession of a company having a list of availale clothes, 
// ranking of the sales the items and a list of clothes that are being requested
public class ClothingInventory {
    private List<Cloth> listClothes; //List of clothes
    private SalesRanking ranking; // The ranking of this inventory's items
    private List<Integer> requestList; //List of requested items
    /*
     * EFFECTS: listClothes is instantiated as an empty list; ranking is instantiated
     */
    public ClothingInventory(){
        //stub   
    }
    /*
     * MODIFIES: this
     * EFFECTS: Adds a cloth object to the list
     */
    public void addCloth(Cloth cloth){
        //stub   
    }

    // EFFECTS: returns a list of clothes and their id
    public List<Cloth> viewClothes(){
        return null;
    }
    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID and removes the corresponding cloth. 
     *          If the item is found, it returns true, false otherwise
     */
    public boolean removeCloth(int clothId){
        return false;

    }

    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID and removes the corresponding cloth 
     *          while incrementing its purchase count and updating ranking. If the item is found, it returns true;
     *          false otherwise
     */
    public boolean buyItem(int clothId){
        return false;

    }
    public SalesRanking getRanking(){
        return null;
    }
    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID. 
     *          If the item isn't found it adds the item to the requestList and returns true ; false otherwise
     */
    public boolean requestItem(int clothId){
        return false;
    }
    /*
     * REQUIRES: inputList.length() > 0
     * EFFECTS: Searches the inputList for a cloth with the itemId. If found, the index in the list is returned;
     *          otherwise, -1 is returned
     */
    public int searchListForClothes(List<Cloth> inputList,int itemId){
        return -1;
    }

}
