package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import persistence.*;
// Represents an inventory of clothes in possession of a company having a list of availale clothes, 
// ranking of the sales the items and a list of clothes that are being requested 
public class ClothingInventory implements Writable {
    private List<Cloth> listClothes; // List of clothes
    private SalesRanking ranking; // The ranking of this inventory's items
    private List<Integer> requestList; // List of requested items
    /*
     * EFFECTS: listClothes and requestList are instantiated as an empty lists;
     * ranking is instantiated
     */

    public ClothingInventory() {
        listClothes = new ArrayList<>();
        ranking = new SalesRanking();
        requestList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds a cloth object to the list
     */
    public void addCloth(Cloth cloth) {
        listClothes.add(cloth);
    }

    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID and
     * removes the corresponding cloth.
     * If the item is found, it returns true, false otherwise
     */
    public boolean removeCloth(int clothId) {
        int foundIdIndex = searchListForClothes(clothId);
        if (foundIdIndex == -1) {
            return false;
        }
        listClothes.remove(foundIdIndex);
        return true;
    }

    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID and
     * removes the corresponding cloth
     * while updating ranking. If the item is found, it returns true;
     * false otherwise
     */
    public boolean buyItem(int clothId) {
        int foundIdIndex = searchListForClothes(clothId);
        if (foundIdIndex == -1) {
            return false;
        }
        ranking.update(listClothes.get(foundIdIndex));
        ranking.organize();
        listClothes.remove(foundIdIndex);
        return true;

    }

    /*
     * REQUIRES: clothId is non-negative and clothId is <= 9999
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID.
     * If the item isn't found it adds the item's id to the requestList and returns
     * true ; false otherwise
     */
    public boolean requestItem(int clothId) {
        int foundIdIndex = searchListForClothes(clothId);
        if (foundIdIndex != -1) {
            return false;
        }
        requestList.add(clothId);
        return true;
    }

    /*
     * REQUIRES: inputList.length() > 0
     * EFFECTS: Searches the clothing List for a cloth with the itemId. If found,
     * the index of its first occurence in the list is returned; otherwise, -1 is
     * returned
     */
    public int searchListForClothes(int itemId) {
        int length = listClothes.size();
        for (int i = 0; i < length; i++) {
            if (listClothes.get(i).getId() == itemId) {
                return i;
            }
        }
        return -1;
    }

    // EFFECTS: returns a sequence of clothes and their id in order; if the ranking
    // is empty, a message of this case is returned
    public String displayRanking() {
        if (ranking.getRanking().isEmpty()) {
            return "No clothes have been bought!";
        }
        String message = "Sales Ranking:";
        for (Cloth cloth : ranking.getRanking()) {
            message += " \n-" + cloth.getColor() + " " + cloth.getClothType() + " (" + cloth.getPurchaseCount() + ") "
                    + "- " + cloth.getId();
        }
        return message;
    }

    // EFFECTS: returns a sequence of clothes and their id; if the list is empty, a
    // message of this case is returned
    public String viewClothes() {
        if (listClothes.isEmpty()) {
            return "There are no clothes added!";
        }
        String message = "Current Clothes:";
        for (Cloth cloth : listClothes) {
            message += " \n-" + cloth.getColor() + " " + cloth.getClothType() + " - " + cloth.getId();
        }
        return message;
    }

    public SalesRanking getRanking() {
        return this.ranking;
    }

    public List<Cloth> getInventoryList() {
        return this.listClothes;
    }

    public int getRequestSize() {
        return this.requestList.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("clothes",clothesToJson());
        json.put("requests",requestsToJson());
        json.put("sales",salesToJson());


        return json;
       
    }
    //EFFECTS: returns the list of clothes as a JSONARRAY
    private JSONArray clothesToJson(){
        JSONArray arrayJ = new JSONArray();
        for (Cloth cloth : listClothes) {
            arrayJ.put(cloth.toJson());
        }
        return arrayJ;
    }
     //EFFECTS: returns the list of sold clothes as a JSONARRAY
    private JSONArray salesToJson(){
        JSONArray arrayJ = new JSONArray();
        for (Cloth cloth : ranking.getRanking()) {
            arrayJ.put(cloth.toJson());
        }
        return arrayJ;
    }
    //EFFECTS: returns the list of requests as a JSONARRAY
    private JSONArray requestsToJson(){
        JSONArray arrayJ = new JSONArray();
        for (int id : requestList) {
            arrayJ.put((new JSONObject()).put("id", id));
        }
        return arrayJ;
    }

}
