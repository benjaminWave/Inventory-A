package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.Cloth;
import model.ClothingInventory;
import persistence.JsonReader;
import persistence.JsonWriter;


public class ClothApp {
    private Scanner input;
    private ClothingInventory inventory;
    private boolean canRun;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/inventory.json";

    //MODIFIES: this 
    // EFFECTS: Creates a JsonWriter and JsonReader; starts the startMenu method
    public ClothApp(){
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startMenu();
    }

    // EFFECTS: Prints out the messages at the menu
    private void welcome() {
        System.out.println("Welcome to your inventory. Select one of the following options:");
        System.out.println("type 'q' to view the list of clothing available for purchase");
        System.out.println("type 'e' to view the sales ranking of sold clothing");
        System.out.println("type 'a' to add a cloth item");
        System.out.println("type 'r' to request a cloth item");
        System.out.println("type 's' to save this inventory");
        System.out.println("type 'l' to load a inventory");
        System.out.println("type 'x' to exit");
    }

    // EFFECTS: Creates scanner and inventory; handles input
    private void startMenu() {
        canRun = true;
        input = new Scanner(System.in);
        inventory = new ClothingInventory();
        while (canRun == true) {
            welcome();
            String key = input.next();
            if (key.equals("q")) {
                handleItemViewing();
            } else if (key.equals("e")) {
                handleRanking();
            } else if (key.equals("a")) {
                handleAdding();

            } else if (key.equals("r")) {
                handleRequests();
            } else if (key.equals("s")) {
                saveFile();;
            } else if (key.equals("l")) {
                loadFile();;

            } else if (key.equals("x")) {
                canRun = false;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes requests from users for certain items
     */
    private void handleRequests() {
        boolean continueRequest = true;
        while (continueRequest == true) {
            System.out.println("enter item ID: ");
            int id = input.nextInt();
            if (inventory.requestItem(id) == false) {
                System.out.println("Item is in Inventory!");
            } else {
                System.out.println(id + " has been requested! Enter x to menu or any key to continue");
                String key = input.next();
                if (key.equals("x")) {
                    continueRequest = false;
                }
            }
        }
    }

    // EFFECTS: displays the list of current items
    private void handleItemViewing() {
        String message = inventory.viewClothes();
        boolean continueViewing = true;
        System.out.println(message);
        if (message.equals("There are no clothes added!")) {
            ;
        } else {
            while (continueViewing == true) {
                System.out.println("enter 'q' to purchase an item");
                System.out.println("enter 'e' to delete an item without purchasing");
                System.out.println("enter 'x' to return to the menu");
                String key = input.next();
                if (key.equals("q")) {
                    handlePurchase();
                } else if (key.equals("e")) {
                    handleDeletion();
                } else if (key.equals("x")) {
                    continueViewing = false;
                }
            }
        }
    }

    // EFFECTS: displays the ranking
    private void handleRanking() {
        String message = inventory.displayRanking();
        System.out.println(message);
        if (message.equals("No clothes have been bought!")) {
            ;
        } else {
            System.out.println("Enter any key to return to menu");
            input.next();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes requests from users to delete certain items based on id
     */
    private void handleDeletion() {
        System.out.println("enter item ID: ");
        int id = input.nextInt();
        if (inventory.removeCloth(id) == true) {
            System.out.println("Successfully deleted item " + id);
        } else {
            System.out.println("That item is not in the inventory!");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes requests from users to buy certain items based on id
     */
    private void handlePurchase() {
        System.out.println("enter item ID: ");
        int id = input.nextInt();
        if (inventory.buyItem(id) == true) {
            System.out.println("Successfully bought item " + id);
        } else {
            System.out.println("That item is not available for purchase!");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Allows the user to add an item of a specific color, type and id
     */
    private void handleAdding() {
        boolean continueAdding = true;
        while (continueAdding == true) {
            System.out.println("enter the color of the item");
            String color = input.next();
            System.out.println("enter the type of cloth (Shirt or Trousers)");
            String type = input.next();
            System.out.println("enter the id of the item");
            int id = input.nextInt();
            inventory.addCloth(new Cloth(color, type, id));
            System.out.println("All Done! Enter x to return to the menu or any other key to add a new item");
            String key = input.next();
            if (key.equals("x")) {
                continueAdding = false;

            }

        }
    }

    /*private void handleSaving(){
        
    }
    private void handleLoading(){

    }
    /*
    MODIFIES: this
    EFFECTS: saves the inventory object to file
    */
    private void saveFile() {
        try{
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            System.out.println("The inventory has been successfully saved to file!"+JSON_STORE);
        } catch (IOException e) {
            System.out.println("ERROR, File" +JSON_STORE+ " cannot be written!");
        }
        
    }
    /*
    MODIFIES: this
    EFFECTS: replaces the current inventory with one loaded from file
    */
    private void loadFile(){
        try{
            this.inventory = jsonReader.read();
            System.out.println("The inventory has been successfully loaded from file!"+JSON_STORE);
        }catch (IOException e) {
            System.out.println("ERROR, File" +JSON_STORE+ " not Found!");
        }
    }
}
