package persistence;
import org.json.*;

import model.Cloth;
import model.ClothingInventory;

import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
//Represents a reader that reads saved JSON data and converts it into an inventory object
public class JsonReader {
    private String source;
    //EFFECTS: source is set to address
    public JsonReader(String address){
        this.source = address;
    }
    //EFFECTS: constructs an inventory object from JSON data and returns it
    // throws IOException if an error occurs reading data from file
    public ClothingInventory read() throws IOException{
        String data = readFile();
        JSONObject jsonObject = new JSONObject(data);
        return parseInventory(jsonObject);
    }
    //EFFECTS: constructs a string from the source file and returns it
    private String readFile() throws IOException{
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
    //EFFECTS: constructs an inventory object from the jsonObject and returns it
    private ClothingInventory parseInventory(JSONObject jsonObject){
        ClothingInventory inventory = new ClothingInventory();
        addClothes(inventory, jsonObject);
        addRequests(inventory, jsonObject);
        return inventory;
    }
    //EFFECTS: constructs Clothes from the jsonObject and adds them to inventory
    private void addClothes(ClothingInventory inventory,JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("clothes");
        for (Object object:jsonArray){
            JSONObject currentObject = (JSONObject)object;
            addCloth(inventory,currentObject);
        }
    }
    //EFFECTS: constructs an instance of a cloth from the jsonObject and adds it to inventory
    private void addCloth(ClothingInventory inventory,JSONObject jsonObject){
        Cloth cloth = new Cloth(jsonObject.getString("color"), jsonObject.getString("type"), jsonObject.getInt("id"));
        inventory.addCloth(cloth);
        buy(inventory,cloth,jsonObject);
    }
    //EFFECTS: increments purchase count of an item buy repeatedly buying and adding it to the inventory  
    private void buy(ClothingInventory inventory,Cloth cloth,JSONObject jsonObject){
        int count = 0;
        int max = jsonObject.getInt("count");
        while (max > count ){
            inventory.buyItem(cloth.getId());
            inventory.addCloth(cloth);
            count++;
        }
    }
    //EFFECTS: parses the RequestList and adds it to the inventory
    private void addRequests(ClothingInventory inventory,JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("requests");
        for (Object object:jsonArray){
            JSONObject currentObject = (JSONObject)object;
            inventory.requestItem(currentObject.getInt("id"));
        }
    }
}
