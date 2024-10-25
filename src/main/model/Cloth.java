package model;

import org.json.JSONObject;

import persistence.Writable;

//Represents a cloth object that possesses an indentifier (id), color, clothType (Shirt or Trousers), 
//and how many times it has been purchased (purchaseCount)
public class Cloth implements Writable {
    private int id; // Unique identifier of the item
    private String color; // Color of the cloth
    private int purchaseCount; // The amount of times the item has been bought
    private String clothType;

    /*
     * REQUIRES: itemColor and itemType have a non-zero length; itemId is
     * non-negative and itemId is <= 9999;
     * itemColor and itemType are named appropriately
     * EFFECTS: color is set to itemColor; clothType is set to itemType; id is set
     * to itemId; purchaseCount is set to 0
     */
    public Cloth(String itemColor, String itemType, int itemId) {
        this.color = itemColor;
        this.clothType = itemType;
        this.id = itemId;
        this.purchaseCount = 0;

    }

    /*
     * MODIFIES: this
     * EFFECTS: Increments the value of purchaseCount by 1
     */
    public void buy() {
        this.purchaseCount++;
    }

    public int getId() {
        return this.id;
    }

    public String getColor() {
        return this.color;
    }

    public String getClothType() {
        return this.clothType;
    }

    public int getPurchaseCount() {
        return this.purchaseCount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", clothType);
        json.put("color", color);
        json.put("id", id);
        json.put("count", purchaseCount);
        return json;
    }
}
