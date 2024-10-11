package model;
import java.util.List;
import java.util.ArrayList;
// Represents the ranking of a list  of items, itemList, based on sales
public class SalesRanking {
    private List<Cloth> itemList; //List of clothes
    /*
     * EFFECTS: itemList is instantiated as an empty list;
     */
    public SalesRanking(){
         this.itemList = new ArrayList<>();
    }
    /*
     * MODIFIES: this, cloth
     * EFFECTS: Adds cloth to the itemList if an instance of an item with them same ID does not already exist in the list;
     *          it modifies the purchaseCount of the existing cloth item
     */
    public void update(Cloth cloth){
        if (itemList.isEmpty()){
            cloth.buy();
            this.itemList.add(cloth);
        }
        else{
            int searchId = cloth.getId();
            boolean canAdd = true;
            for (Cloth item:this.itemList){
                if (item.getId() == searchId){
                    canAdd = false;
                    item.buy();
                    break;
                }
            }
            if (canAdd == true){
                cloth.buy();
                this.itemList.add(cloth);
            } 
        }
    }

    /*
     * REQUIRES: itemList.size() >1
     * MODIFIES: this
     * EFFECTS: Sorts itemList from the cloth with the highest purchase count to the lowest
    */
    public void organize(){
        int length = this.itemList.size();
        for (int i =length-1;i>0;i--){
            int currentCount = itemList.get(i).getPurchaseCount();
            for (int j=i-1; j>=0;j--){
                int otherCount = itemList.get(j).getPurchaseCount();
                if (currentCount > otherCount){
                    Cloth temp = itemList.get(j);
                    this.itemList.set(j, itemList.get(i));
                    this.itemList.set(i,temp);
                }
            }
        }
        
    }

    
    public List<Cloth> getRanking(){
        return this.itemList;
    }
}
