package model;
import java.util.List;
// Represents an inventory of clothes in possession of a company having a list of availale clothes
public class ClothingInventory {
    private List<Cloth> listClothes; //List of clothes

    /*
     * EFFECTS: listClothes is instantiated as an empty list
     * 
     */
    public ClothingInventory(){
        
    }
    /*
     * MODIFIES: this
     * EFFECTS: Adds a cloth object to the list
     */
    public void addCloth(Cloth cloth){

    }

    // EFFECTS: returns a list of clothes and their id
    public List<Cloth> viewClothes(){
        return null;
    }
    /*
     * REQUIRES: Cloth object corresponding to the clothID must be present in listClothes
     * MODIFIES: this
     * EFFECTS: Searches the clothing list for an instance of the given ID and removes the corresponding cloth 
     */
    public boolean removeCloth(int clothID){
        return false;

    }

    public boolean buyItem(int clothID){
        return false;

    }
}
