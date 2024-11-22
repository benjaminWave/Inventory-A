package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.List;

import model.Cloth;
import java.awt.*;
import java.awt.event.*;

//Represents the main UI component 
public class ClothGUI extends JFrame implements ActionListener, ChangeListener {

    private int width = 700;
    private int height = 1500;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JTabbedPane tab;
    private MenuPanel menu;
    private InventoryHandler handler;
    private ListContainer listTab;
    private RequestContainer requestTab;
    private String mode;
    private RankingContainer rankTab;

    // EFFECTS: invokes the JFrame superclass and sets the size,close operation and
    // layout to card; creates a JTabbedPane and InventoryHandler; creates the
    // MenuBar, main Panel and finishes setting up the ClothGUI
    public ClothGUI() {
        super("Clothing Inventory");
        tab = new JTabbedPane();
        handler = new InventoryHandler();
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        createMenuBar();
        createMain();
        finish();

    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a JMenuBar for the UI with options to load and save
     */
    private void createMenuBar() {
        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.saveFile = new JMenuItem("Save");
        this.loadFile = new JMenuItem("Load");
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        saveFile.setActionCommand("Save");
        saveFile.addActionListener(this);
        loadFile.setActionCommand("Load");
        loadFile.addActionListener(this);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and adds the main panel to this
     */
    private void createMain() {
        menu = new MenuPanel(this);
        tab.addTab("MENU", menu);
        tab.getModel().addChangeListener(this);
        tab.getSelectedIndex();
        add(tab, BorderLayout.CENTER);

    }

    /*
     * MODIFIES:this
     * EFFECTS: sets the resizable property to false and makes the GUI visible
     */
    private void finish() {

        setResizable(false);
        setVisible(true);
    }

    /*
     * REQUIRES: message.length() > 1
     * MODIFIES: this
     * EFFECTS: Creates a popup menu that questions the user, yes or no, based on
     * message
     */
    private JPopupMenu questionUser(String message) {
        JPopupMenu query = new JPopupMenu();

        JMenuItem yes = new JMenuItem("Yes");
        yes.setActionCommand("Yes");
        this.mode = message;
        yes.addActionListener(this);
        JMenuItem no = new JMenuItem("No");
        no.setActionCommand("No");
        no.addActionListener(this);
        JLabel title = new JLabel("Are you sure you want to " + message + " ?");
        query.add(title);
        query.add(yes);
        query.add(no);

        return query;
    }

    public InventoryHandler getHandler() {
        return handler;
    }

    // MODIFIES: this
    // EFFECTS: sets the current tab to the List Panel and moves the currentIndex to
    // 1
    public void viewItems() {
        listTab = new ListContainer(this);
        tab.addTab("LIST", listTab);
        tab.setSelectedIndex(1);
        listTab.update(handler.handleListViewing(), handler.getClothes());
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the current tab to the Sales Panel and moves the currentIndex
     * to 1
     */
    public void viewRanking() {
        rankTab = new RankingContainer(this);
        tab.addTab("SALES", rankTab);
        tab.setSelectedIndex(1);
        rankTab.update(handler.handleRankViewing(), handler.getRanking());

    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the current tab to the Request Panel and moves the currentIndex
     * to 1
     */
    private void requestItem() {
        requestTab = new RequestContainer(this);
        tab.addTab("REQUEST", requestTab);
        tab.setSelectedIndex(1);
    }

    // EFFCTS: handles action events
    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action == "Save") {
            JPopupMenu query = questionUser("save");
            query.show(this, 50, 50);

        } else if (action == "Load") {
            JPopupMenu query = questionUser("load");
            query.show(this, 50, 50);
        } else if (action == "Yes") {
            if (mode == "save") {
                saveFile();
            } else if (mode == "load") {
                loadFile();
            }
        } else if (action == "View List") {
            viewItems();

        } else if (action == "View Ranking") {

            viewRanking();
        } else if (action == "Request") {
            requestItem();
        }
    }

    // EFFECTS: saves to file and creates a JPopupMenu based on the success of
    // saving to file
    private void saveFile() {
        JPopupMenu newMenu = new JPopupMenu();
        boolean hasWorked = handler.handleSave();
        JLabel messageLabel = (hasWorked == true) ? new JLabel("Successfully saved to file!")
                : new JLabel("Could not save to file!");

        newMenu.add(messageLabel);
        newMenu.show(this, 50, 50);

    }

    // MODIFIES: this
    // EFFECTS: loads from file and creates a JPopupMenu based on the success of
    // loading file; replaces the currentTab with an updated tab if the current tab
    // is not the main panel
    private void loadFile() {
        JPopupMenu newMenu = new JPopupMenu();
        boolean hasWorked = handler.handleLoad();
        JLabel messageLabel = (hasWorked == true) ? new JLabel("Successfully loaded from file!")
                : new JLabel("Could not load to file!");

        newMenu.add(messageLabel);
        newMenu.show(this, 50, 50);
        if (tab.getSelectedIndex() == 1) {
            if (tab.getSelectedComponent() == listTab) {

                viewItems();
                tab.remove(1);
            }
            if (tab.getSelectedComponent() == rankTab) {

                viewRanking();
                tab.remove(1);
            }
            if (tab.getSelectedComponent() == requestTab) {
                requestItem();
                tab.remove(1);
            }
        }

    }

    // EFFECTS: receives data from the listTab, confirms if the corresponding action
    // (add, buy and remove) was successful and updates the listTab
    public boolean receiveData(String color, String type, int id, String action) {
        if (action == "added") {
            handler.handleAdd(color, type, id);
            String items = handler.handleListViewing();
            List<Cloth> clothes = handler.getClothes();
            listTab.update(items, clothes);
            return true;
        } else if (action == "removed") {
            boolean confirm = handler.handleRemove(id);
            String items = handler.handleListViewing();
            List<Cloth> clothes = handler.getClothes();
            listTab.update(items, clothes);
            return confirm;
        } else if (action == "bought") {
            boolean confirm = handler.handleBuy(id);
            String items = handler.handleListViewing();
            List<Cloth> clothes = handler.getClothes();
            listTab.update(items, clothes);
            return confirm;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: If the tab is changed to the main panel, the other tab is removed;
    @Override
    public void stateChanged(ChangeEvent e) {
        if (tab.getSelectedIndex() == 0) {
            tab.remove(1);
        }
    }

}
