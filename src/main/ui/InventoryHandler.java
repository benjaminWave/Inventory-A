package ui;

import java.io.IOException;
import java.util.List;

import model.Cloth;
import model.ClothingInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

//Represents  a class that handles changes and data retrieval from an instance of a ClothingInventory
public class InventoryHandler {
    private ClothingInventory inventory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/inventory.json";

    // EFFECTS: creates a JsonWriter, JsonReader and ClothingInventory
    public InventoryHandler() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        inventory = new ClothingInventory();
    }

    // EFFECTS: returns viewClothes() string as confirmation of list status
    public String handleListViewing() {
        return inventory.viewClothes();
    }

    // MODIFIES: this
    // EFFECTS: add a new Cloth item to the inventory based on the input
    public void handleAdd(String color, String type, int id) {
        inventory.addCloth(new Cloth(color, type, id));
    }

    public List<Cloth> getClothes() {
        return inventory.getInventoryList();
    }

    // EFFECTS: returns displayRanking() string as confirmation of sales status
    public String handleRankViewing() {
        return inventory.displayRanking();
    }

    public List<Cloth> getRanking() {
        return inventory.getRanking().getRanking();
    }

    // MODIFIES: this
    // EFFECTS: removes a cloth item based on id and confirms if this was successful
    public boolean handleRemove(int id) {
        return inventory.removeCloth(id);
    }

    // MODIFIES: this
    // EFFECTS: buy a cloth item based on id and confirms if this was successful
    public boolean handleBuy(int id) {
        return inventory.buyItem(id);
    }

    /*
     * MODIFIES: this
     * EFFECTS: saves the inventory object to file and confirms if this was
     * successful
     */
    public boolean handleSave() {
        boolean confirm = false;
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            confirm = true;
        } catch (IOException e) {
            confirm = false;
        }
        return confirm;
    }

    /*
     * MODIFIES: this
     * EFFECTS: replaces the current inventory with one loaded from file and
     * confirms if this was successful
     */
    public boolean handleLoad() {
        boolean confirm = false;
        try {
            inventory = jsonReader.read();
            confirm = true;
        } catch (IOException e) {
            confirm = false;
        }
        return confirm;
    }

    // EFFECTS: requests a cloth item based on id and confirms if this was
    // successful
    public boolean handleRequest(int id) {
        return inventory.requestItem(id);
    }

}
