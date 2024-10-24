package persistence;
import org.json.*;

import model.ClothingInventory;

import java.io.*;
import java.nio.*;
//Represents a reader that reads saved JSON data and converts it into an inventory object
public class JsonReader {
    private String source;
    //EFFECTS: source is set to address
    public JsonReader(String address){
    }
    //EFFECTS: constructs an inventory object from JSON data and returns it
    // throws IOException if an error occurs reading data from file
    public ClothingInventory read() throws IOException{
        return null;
    }
    //EFFECTS: constructs a string from the source file and returns it
    private String readFile() throws IOException{
        return null;
    }
    //EFFECTS: constructs an inventory object from the jsonObject and returns it
    private ClothingInventory parseInventory(JSONObject jsonObject){
        return null;
    }
    //EFFECTS: constructs Clothes from the jsonObject and adds them to inventory
    private void addClothes(ClothingInventory inventory,JSONObject jsonObject){

    }
    //EFFECTS: constructs an instance of a cloth from the jsonObject and adds it to inventory
    private void addCloth(ClothingInventory inventory,JSONObject jsonObject){

    }
    //EFFECTS: increments purchase count of an item
    private void buy(ClothingInventory inventory,int id){

    }
    //EFFECTS: parses the RequestList and adds it to the inventory
    private void addRequests(ClothingInventory inventory,JSONObject jsonObject){
        
    }
}
