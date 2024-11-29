# My Personal Project
## **Project Proposal**
My project, *Inventory A*, is a desktop software that functions as an inventory for shirts and trousers in a clothing store. It takes a list of incoming clothing items and classifies them based on the clothing type and size.  The application will also have a feature to keep track of the purchasing trends of certain items by recording the number of sales for each item. This application will be used by the business to keep track of what clothes are available, and by the popularity of the item, what items they need to restock. This application will also have a feature to request for certain items a customer is interested in. This project interests me as it could apply to a professional setting; in particular, aiding the organization of items in a clothing business to keep track of trends and handle requests from customers.


## **User Stories**
- As a user, I want to be able to add received clothing to my clothing inventory
- As a user, I want to be able to view the list of clothing available for purchase
- As a user, I want to be able to see the sales for particular clothing items and an overall ranking of the most purchased items
- As a user, I want to be able to delete an instance of a purchased item from the clothing inventory
- As a user, I want to be able to send a request for a resupply of a particular item
- As a user, I want to be able to save my current inventory to file
- As a user, I want to be able to load my inventory from file


# Instructions for End User

- You can  add a cloth item to the UI by first clicking on the "View the list of clothing available for purchase" button in the "MENU" tab. This will lead you to the "LIST" tab. Thereafter, click on the "Add a cloth item" button. Then select the color, cloth type and enter a 4 digit id. Click the enter button. The cloth item will appear at the upper panel
- You can remove a cloth item by following the previous instructions to the "LIST" tab. Select the "Remove a cloth item" button. Therafter enter the id of the item you wish to remove from the list above and click the enter button. The item should be removed from the list

- You can buy a cloth item by following the previous instructions to the "LIST" tab. Select the "Buy a cloth item" button. Therafter enter the id of the item you wish to buy from the list above and click the enter button. The item should be removed from the list, but is now present in the sales ranking

- You can view the sales ranking by clicking on the "View the sales ranking of sold clothing" button in the "MENU" tab. This will lead you to the "SALES" tab where the items should be displayed, if any have been purchased.
- You can add a request by clicking on the "Request item" option in the "MENU" tab. Enter the id of the item you want request and press the enter button.
- You can locate my visual component when trying to add a cloth item. After selecting the color, the next stage is the type of cloth. Here, you will see two options, shirt and trousers with their respective images. Another visual component is present any time you enter the id of an item, such as in the next step. The enter button uses a stylized image of an enter button
- You can save the state of my application by clicking the file option at the top left corner. Click the save option and verify the save
- You can reload the state of my application by clicking the file option at the top left corner. Click the load option and verify the load, the UI should now update to match the file that was loaded.



# Phase 4: Task 2
*Sample of Console after closing GUI*

Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
The sales ranking of the inventory has been updated and organized
Fri Nov 29 13:35:07 PST 2024
Cloth of ID: 4332 has been removed from the inventory
Fri Nov 29 13:35:13 PST 2024
Cloth of ID: 1234 has been requested to the inventory
Fri Nov 29 13:35:15 PST 2024
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
The sales ranking of the inventory has been updated and organized
Fri Nov 29 13:35:07 PST 2024
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
The sales ranking of the inventory has been updated and organized
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
The sales ranking of the inventory has been updated and organized
Fri Nov 29 13:35:07 PST 2024
Cloth of ID: 4332 has been removed from the inventory
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
Cloth of ID: 2000 has been bought from the inventory
Cloth of ID: 2000 has been bought from the inventory
Fri Nov 29 13:35:02 PST 2024
The sales ranking of the inventory has been updated and organized
Fri Nov 29 13:35:07 PST 2024
Cloth of ID: 4332 has been removed from the inventory
Fri Nov 29 13:35:13 PST 2024
Cloth of ID: 1234 has been requested to the inventory
Fri Nov 29 13:35:15 PST 2024
Cloth of ID: 4321 has been requested to the inventory
Fri Nov 29 13:35:17 PST 2024
Cloth of ID: 8888 has been requested to the inventory




# Phase 4: Task 3
In reference to the UML diagram, both the ClothingInventory and SalesRanking are made up of a collection of Cloth objects. Within the code, both add cloth objects to their list, but SalesRanking ranks its received objects for ClothingInventory's use. A change would be to implement a bidirectional relationship between SalesRanking and ClothingInventory with a multiplicity of 1 for each side and let SalesRanking access ClothingInventory's list. This way, only the Inventory handles the creation of the list of clothes. But in this case, the cloth Object would need a new field to show quantity; an alternative to this would be to have 2 lists within the clothing inventory that contains bought clothes. 

Within the code, there is some duplication that could be refactored. In the ClothingInventory class, there is some duplicate code for the buy and remove methods. They both remove clothes from the inventory, but the buy method updates the ranking. I could refactor this by removing the duplication and using a helper method that returns a boolean depending on if a cloth item was found. A similar issue is in ClothGUI where viewItems and viewRankings are very similar and could be merged into a single method that takes a String parameter to show what is being viewed and because both RankingContainer and InventoryContainer extend Container, a Container parameter can be passed as an argument. The ListContainer has a color conversion method that could be refactored into another class that handles that responsibility.



# References
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Referenced from  Java Program to Add a JMenuBar and JButton inside the JFrame
// https://www.geeksforgeeks.org/java-jframe/
// https://www.javatpoint.com/java-jmenuitem-and-jmenu
//https://www.javatpoint.com/java-jpopupmenu
//https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html\
//https://www.javatpoint.com/java-swing

// Referenced from windowListener tutorial
//https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html