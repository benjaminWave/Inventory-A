package model;
//Represents a cloth object that possesses an indentifier (id), color, clothType (Shirt or Trousers), 
//and how many times it has been purchased (purchaseCount)
public class Cloth {
    private int id; // Unique identifier of the item
    private String color; // Color of the cloth
    private int purchaseCount; // The amount of times the item has been bought
    private String clothType;
    /*
     * REQUIRES: itemColor and itemType have a non-zero length; itemId is non-negative and itemId is <= 9999; 
     *           itemColor and itemType are named appropriately
     * EFFECTS: color is set to itemColor; clothType is set to itemType; id is set to itemId; purchaseCount is set to 0
     */
    public Cloth(String itemColor,String itemType,int itemId){
        //stub
    }
    /*
     * MODIFIES: this
     * EFFECTS: Increments the value of purchaseCount by 1
     */
    public void buy(){
        //stub
    }
    public int getId(){
        return -1;
    }
    public String getColor(){
        return null;
    }
    public String getClothType(){
        return null;
    }
    public int getPurchaseCount(){
        return -1;
    }
}
