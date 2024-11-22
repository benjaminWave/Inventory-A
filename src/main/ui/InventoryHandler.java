package ui;

import java.io.IOException;
import java.util.List;

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

    public InventoryHandler() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        inventory = new ClothingInventory();
    }

    public String handleListViewing() {

        return inventory.viewClothes();
    }

    public void handleAdd(String color, String type, int id) {
        inventory.addCloth(new Cloth(color, type, id));
    }

    public List<Cloth> getClothes() {
        return inventory.getInventoryList();
    }

    public String handleRankViewing() {
        return inventory.displayRanking();
    }

    public List<Cloth> getRanking() {
        return inventory.getRanking().getRanking();
    }

    public boolean handleRemove(int id) {
        return inventory.removeCloth(id);
    }

    public boolean handleBuy(int id) {
        return inventory.buyItem(id);
    }

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

    public boolean handleLoad(){
        boolean confirm = false;
        try {
            inventory = jsonReader.read();
            confirm = true;  
        } catch (IOException e) {
            confirm = false;
        }
        return confirm;
    }
   

}
