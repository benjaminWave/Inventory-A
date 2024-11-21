package ui;


import model.Cloth;
import model.ClothingInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

public class InventoryHandler {
    private ClothingInventory inventory;
    private boolean canRun;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/inventory.json";

    public InventoryHandler(){
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        inventory = new ClothingInventory();
    }
    public String handleListViewing(){
        String message = inventory.viewClothes();
        return message;
    }

}
