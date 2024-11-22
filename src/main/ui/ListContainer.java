package ui;

import java.util.List;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Cloth;

//Represents a tab for the List of Clothes
public class ListContainer extends Container implements ActionListener {
    private final Color mainColor = Color.white;
    List<String> thisList = new ArrayList<>();
    private JButton addButton;
    private JButton removeButton;
    private JButton buyButton;
    private ClothGUI parent;
    private JPanel mainPanel;
    private JPanel lastPanel;
    private CardLayout card;
    private ImageIcon shirtImage;
    private ImageIcon trousersImage;
    private Container container;
    private final String[] colors = { "red", "orange", "yellow", "green", "blue", "magenta", "pink", "black", "white" };
    private Map<Color, String> map;
    private String selectedColor;
    private String selectedType;
    private int enteredId;
    private boolean confirmation;
    JScrollPane scroll;
    private JTextField field;

    // EFFECTS: creates a listContainer; creates the lower half container and sets
    // it to a card layout; creates color to string map, the top Panel, the lower
    // panel and the JScroll for the top panel; sets this.parent to parent
    public ListContainer(ClothGUI parent) {
        super();
        container = new Container();
        map = new HashMap<>();
        card = new CardLayout();
        container.setLayout(card);
        mainPanel = initializePanel(new Rectangle(1, 1, parent.getWidth(), parent.getHeight() / 2));
        lastPanel = initializePanel(new Rectangle(1, 1, parent.getWidth(), parent.getHeight() / 2));
        container.setBounds(1, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2);
        add(container);
        add(mainPanel);
        scroll = new JScrollPane(mainPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(1, 1, parent.getWidth() - 15, parent.getHeight() / 2);
        add(scroll);
        createAdd();
        this.parent = parent;
    }

    // EFFECTS: creates and returns a JPanel based on rect
    private JPanel initializePanel(Rectangle rect) {
        JPanel referencePanel = new JPanel();
        referencePanel.setBackground(mainColor);
        referencePanel.setLayout(null);
        referencePanel.setBounds(rect);
        return referencePanel;
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates the list UI based on the list of clothes and message
     */
    public void update(String message, List<Cloth> clothes) {
        mainPanel.removeAll();
        if (message == "There are no clothes added!") {
            JLabel test = new JLabel(message);
            test.setBackground(Color.gray);
            test.setBounds(1, 1, 500, 90);

            mainPanel.add(test);
        } else {
            createList(clothes);
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: creates add,buy and remove buttons. Sets the current panel to the
     * panel containing these
     */
    public void createAdd() {
        container.add(lastPanel);
        container.add("Add", lastPanel);
        card.show(container, "Add");
        addButton = createButton(new Rectangle(1, 1, 300, 50), "Add");
        removeButton = createButton(new Rectangle(310, 1, 300, 50), "Remove");
        buyButton = createButton(new Rectangle(150, 58, 300, 50), "Buy");
        lastPanel.add(addButton);
        lastPanel.add(removeButton);
        lastPanel.add(buyButton);

    }

    // REQUIRES: message.length() > 0
    // EFFECTS: Creates and returns a JButton with Bounds rect and a title based on
    // message
    private JButton createButton(Rectangle rect, String message) {
        JButton referenceButton = new JButton(message + " a cloth item");
        referenceButton.setForeground(Color.white);
        referenceButton.setBackground(Color.gray);
        referenceButton.addActionListener(this);
        referenceButton.setActionCommand(message);
        referenceButton.setBounds(rect);
        return referenceButton;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the lower container's panel to a new panel that has the option
     * to select the color of cloth
     */
    public void colorMessage() {
        JPanel newPanel = initializePanel(
                new Rectangle(1, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2));
        JLabel selectColor = new JLabel("Select the color of the item: ");
        selectColor.setBackground(Color.gray);
        selectColor.setBounds(1, 101, 500, 100);
        newPanel.add(selectColor);

        container.add("Color", newPanel);
        card.show(container, "Color");
        createcolors(newPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the lower container's panel to a new panel that has the option
     * to select the type of cloth
     */
    private void typeMessage() {
        JPanel newPanel = initializePanel(
                new Rectangle(1, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2));
        JLabel select = new JLabel("Select the type of item: ");
        select.setBackground(Color.gray);
        select.setBounds(1, 1, 500, 100);
        newPanel.add(select);

        container.add("Type", newPanel);
        card.show(container, "Type");
        createType(newPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the lower container's panel to a new panel that has the option
     * to enter the cloth's id
     */
    private void idMessage(String use) {
        JPanel newPanel = initializePanel(
                new Rectangle(1, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2));
        JLabel select = new JLabel("Enter the 4 digit id of item: ");
        select.setBackground(Color.gray);
        select.setBounds(1, 1, 500, 100);
        newPanel.add(select);

        container.add("Id", newPanel);
        card.show(container, "Id");
        createId(newPanel, use);
    }

    /*
     * REQUIRES: action.length() > 1
     * MODIFIES: this
     * EFFECTS: resets the lastPanel and lowerContainer; displays a confirmation
     * based on the data sent to this.parent; sets the panel back to the add,remove
     * and buy panel
     */
    private void finishedMessage(String action) {
        lastPanel.removeAll();
        confirmation = parent.receiveData(selectedColor, selectedType, enteredId, action);
        JLabel confirm = new JLabel("Item Successfully " + action);
        if (confirmation == false) {
            confirm.setText("Item not in inventory!");
        }
        container.removeAll();

        confirm.setBounds(1, 100, 300, 100);
        lastPanel.add(confirm);
        createAdd();
    }

    /*
     * REQUIRES: use.length() > 0
     * MODIFIES: this
     * EFFECTS: creates a JTextField to handle user input; creates an enter button
     * that has an action based on use
     * 
     */
    private void createId(JPanel currentPanel, String use) {
        field = new JTextField();
        field.setBounds(1, 100, 100, 20);
        currentPanel.add(field);

        JButton enter = new JButton();
        ImageIcon icon = new ImageIcon(
                "data/images/enter.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(60, 20, 0);
        icon = new ImageIcon(image);
        enter.setIcon(icon);
        enter.setBounds(101, 100, 60, 20);
        currentPanel.add(enter);
        enter.addActionListener(this);
        String cmd = (use == "Add") ? "Enter" : ((use == "Remove") ? "Enter2" : "Enter3");

        enter.setActionCommand(cmd);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds cloth buttons to the currentPanel
     */
    private void createType(JPanel currentPanel) {
        createImages();
        JButton shirtButton = new JButton(shirtImage);
        JLabel shirtLabel = new JLabel("SHIRT");
        shirtButton.setBounds(1, 100, 180, 180);
        shirtLabel.setBounds(1, 280, 80, 80);
        currentPanel.add(shirtButton);
        currentPanel.add(shirtLabel);
        JButton trousersButton = new JButton(trousersImage);
        JLabel trousersLabel = new JLabel("TROUSERS");
        trousersButton.setBounds(200, 100, 180, 180);
        trousersLabel.setBounds(200, 280, 80, 80);
        currentPanel.add(trousersButton);
        currentPanel.add(trousersLabel);
        shirtButton.setActionCommand("Shirt");
        shirtButton.addActionListener(this);
        trousersButton.setActionCommand("Trousers");
        trousersButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets shirtImage and trousersImage to an ImageIcon from file
    private void createImages() {
        shirtImage = new ImageIcon(
                "data/images/shirt.jpg");
        Image image = shirtImage.getImage();
        image = image.getScaledInstance(200, 200, 0);
        shirtImage = new ImageIcon(image);
        trousersImage = new ImageIcon(
                "data/images/trousers.png");
        Image image2 = trousersImage.getImage();
        image2 = image2.getScaledInstance(200, 200, 0);
        trousersImage = new ImageIcon(image2);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds color buttons to currentPanel and updates the map to match
     * color to string
     */
    private void createcolors(JPanel currentPanel) {
        int base = 1;
        for (int i = 0; i < colors.length; i++) {
            JButton redButton = new JButton();
            redButton.setBackground(toColor(colors[i]));
            redButton.setBounds(base + i * 40, 201, 40, 40);
            redButton.setBorderPainted(true);
            currentPanel.add(redButton);
            redButton.setActionCommand("Color");
            redButton.addActionListener(this);
            map.put(toColor(colors[i]), colors[i]);
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a list of JLabels, based on the clothes, that is added to
    // the mainPanel
    private void createList(List<Cloth> clothes) {
        for (Cloth cloth : clothes) {
            JLabel item = new JLabel(cloth.getColor() + " " + cloth.getClothType() + ", ID: " + cloth.getId());
            item.setBounds(1, 1 + clothes.indexOf(cloth) * 20, 500, 20);
            mainPanel.add(item);
        }
    }

    /*
     * REQUIRES: colors contains str
     * EFFECTS: matchs a String str to a color
     */
    private Color toColor(String str) {
        Color color = new Color(0);
        if (str == "red") {
            color = Color.red;
        } else if (str == "orange") {
            color = Color.orange;
        } else if (str == "yellow") {
            color = Color.yellow;
        } else if (str == "green") {
            color = Color.green;
        } else if (str == "blue") {
            color = Color.blue;
        } else if (str == "magenta") {
            color = Color.magenta;
        } else if (str == "pink") {
            color = Color.pink;
        } else if (str == "black") {
            color = Color.black;
        } else if (str == "white") {
            color = Color.white;
        }
        return color;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Handles action events for different buttons; collects data for
     * appropriate events
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand() == "Add") {
            colorMessage();
        } else if (event.getActionCommand() == "Remove" | event.getActionCommand() == "Buy") {
            idMessage(event.getActionCommand());

        } else if (event.getActionCommand() == "Color") {
            String color = map.get(((JButton) event.getSource()).getBackground());
            color = color.toUpperCase();
            selectedColor = color;
            typeMessage();
        } else if (event.getActionCommand() == "Shirt" | event.getActionCommand() == "Trousers") {
            selectedType = event.getActionCommand();
            idMessage("Add");
        } else if (event.getActionCommand() == "Enter") {
            enteredId = Integer.parseInt(field.getText());
            finishedMessage("added");
        } else if (event.getActionCommand() == "Enter2") {
            enteredId = Integer.parseInt(field.getText());
            finishedMessage("removed");
        } else if (event.getActionCommand() == "Enter3") {
            enteredId = Integer.parseInt(field.getText());
            finishedMessage("bought");
        }
    }

}
