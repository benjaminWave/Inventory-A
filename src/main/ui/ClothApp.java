package ui;
import java.util.Scanner;

import model.Cloth;
import model.ClothingInventory;
public class ClothApp {
    private Scanner input;
    private ClothingInventory inventory;
    private boolean canRun;
    //EFFECTS: Starts the runProgram method
    public ClothApp(){
        startMenu();
    }

    private void startMenu(){
        canRun = true;
        input = new Scanner(System.in);
        inventory = new ClothingInventory();
        while (canRun == true){
            System.out.println("Welcome to your inventory. Select one of the following options:");
            System.out.println("type 'q' to view the list of clothing available for purchase");
            System.out.println("type 'e' to view the sales ranking of sold clothing");
            System.out.println("type 'r' to add a cloth item");
            System.out.println("type 'x' to exit");
            String key = input.next();
            if (key.equals("q")){
                handleItemViewing();
            }
            else if (key.equals("e")){
                handleDeletion();
            }
            else if (key.equals("r")){
                handleAdding();
           
            }
            else if (key.equals("x")){
                canRun = false;
            }
            else{
                System.out.println("Invalid input!");
            }
        }
    }

    private void handleItemViewing(){
        String message = inventory.viewClothes();
        boolean continueViewing = true;
        System.out.println(inventory.viewClothes());
        if (message.equals("There are no clothes added!"));
        else{
            while (continueViewing == true){
                System.out.println("enter 'q' to purchase an item");
                System.out.println("enter 'e' to delete an item without purchasing");
                System.out.println("enter 'x' to return to the menu");
                String key = input.next();
                if (key.equals("q")){
                    handlePurchase();
                }
                else if (key.equals("e")){
                    handleDeletion();
                }
                else if (key.equals("x")){
                    continueViewing = false;
                }
            }
        }
    }
    private void handleDeletion(){
        System.out.println("enter item ID: ");
        int id = input.nextInt();
        if (inventory.removeCloth(id)== true){
            System.out.println("Successfully deleted item "+id);
        }
        else{
            System.out.println("That item is not in the inventory!"); 
        }
    }
    private void handlePurchase(){
        System.out.println("enter item ID: ");
        int id = input.nextInt();
        if (inventory.buyItem(id)== true){
            System.out.println("Successfully bought item "+id);
        }
        else{
            System.out.println("That item is not available for purchase!"); 
        }
    }
    private void handleAdding(){
        boolean continueAdding = true;
        while (continueAdding == true){
            System.out.println("enter the color of the item");
            String color = input.next();
            System.out.println("enter the type of cloth (Shirt or Trousers)");
            String type = input.next();
            System.out.println("enter the id of the item");
            int id = input.nextInt();
            inventory.addCloth(new Cloth(color,type,id));
            System.out.println("All Done! Enter x to return to the menu or any other key to add a new item");
            String key = input.next();
            if (key.equals("x")){
                continueAdding = false;
                
            }

        }
    }
}
