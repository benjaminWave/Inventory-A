package ui;

import java.util.List;
import java.util.*;
import java.util.jar.JarEntry;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.xml.sax.SAXException;

import model.Cloth;

public class ListContainer extends Container implements ActionListener {
    private final Color COLOR = Color.white;
    private JList list;
    List<String> thisList = new ArrayList<>();
    private JButton addButton;
    private JButton removeButton;
    private JButton buyButton;
    private ClothUI parent;
    private JPanel mainPanel;
    private JPanel lastPanel;
    private CardLayout card;
    private ImageIcon shirtImage;
    private ImageIcon trousersImage;
    private Container container;
    private final String[] COLORS = { "red", "orange", "yellow", "green", "blue", "magenta", "pink", "black", "white" };
    private Map<Color, String> map;
    private String selectedColor;
    private String selectedType;
    private int enteredId;
    private boolean confirmation;
    JScrollPane scroll;
    private JTextField field;

    public ListContainer(ClothUI parent) {
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
        list = new JList<>();
        this.parent = parent;
        mainPanel.add(list);

    }

    private JPanel initializePanel(Rectangle rect) {
        JPanel referencePanel = new JPanel();
        referencePanel.setBackground(COLOR);
        referencePanel.setLayout(null);
        referencePanel.setBounds(rect);
        return referencePanel;
    }

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

    public void createAdd() {
        container.add(lastPanel);

        container.add("Add", lastPanel);
        card.show(container, "Add");
        addButton = new JButton("Add a cloth item");
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.gray);

        addButton.addActionListener(this);
        addButton.setActionCommand("Add");

        addButton.setBounds(1, 1, 300, 50);
        lastPanel.add(addButton);

        removeButton = new JButton("Remove a cloth item");
        removeButton.setForeground(Color.white);
        removeButton.setBackground(Color.gray);
        removeButton.addActionListener(this);
        removeButton.setActionCommand("Remove");
        removeButton.setBounds(310, 1, 300, 50);
        lastPanel.add(removeButton);

        buyButton = new JButton("Buy a cloth item");
        buyButton.setForeground(Color.white);
        buyButton.setBackground(Color.gray);
        buyButton.addActionListener(this);
        buyButton.setActionCommand("Buy");
        buyButton.setBounds(150, 58, 300, 50);
        lastPanel.add(buyButton);

    }

    public void colorMessage() {
        JPanel newPanel = initializePanel(
                new Rectangle(1, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2));
        JLabel selectColor = new JLabel("Select the color of the item: ");
        selectColor.setBackground(Color.gray);
        selectColor.setBounds(1, 101, 500, 100);
        newPanel.add(selectColor);

        container.add("Color", newPanel);
        card.show(container, "Color");
        createColors(newPanel);

    }

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

    private void createId(JPanel currentPanel, String use) {
        field = new JTextField();
        field.setBounds(1, 100, 100, 20);
        currentPanel.add(field);

        JButton enter = new JButton();
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\user\\Documents\\CPSC210 folder\\0000\\ProjectStarter\\data\\images\\enter.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(60, 20, 0);
        icon = new ImageIcon(image);
        enter.setIcon(icon);
        enter.setBounds(101, 100, 60, 20);
        currentPanel.add(enter);
        enter.addActionListener(this);
        String cmd = (use == "Add") ? "Enter" : ((use == "Remove") ? "Enter2" : "Enter3");
        System.out.println(cmd);
        enter.setActionCommand(cmd);
    }

    private void createType(JPanel currentPanel) {
        shirtImage = new ImageIcon(
                "C:\\Users\\user\\Documents\\CPSC210 folder\\0000\\ProjectStarter\\data\\images\\shirt.jpg");
        Image image = shirtImage.getImage();
        image = image.getScaledInstance(200, 200, 0);
        shirtImage = new ImageIcon(image);
        JButton shirtButton = new JButton(shirtImage);

        JLabel shirtLabel = new JLabel("SHIRT");

        shirtButton.setBounds(1, 100, 180, 180);
        shirtLabel.setBounds(1, 280, 80, 80);
        currentPanel.add(shirtButton);
        currentPanel.add(shirtLabel);

        trousersImage = new ImageIcon(
                "C:\\Users\\user\\Documents\\CPSC210 folder\\0000\\ProjectStarter\\data\\images\\trousers.png");
        Image image2 = trousersImage.getImage();
        image2 = image2.getScaledInstance(200, 200, 0);
        trousersImage = new ImageIcon(image2);
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

    private void createColors(JPanel currentPanel) {
        // red.
        int base = 1;
        for (int i = 0; i < COLORS.length; i++) {
            JButton redButton = new JButton();
            redButton.setBackground(toColor(COLORS[i]));
            redButton.setBounds(base + i * 40, 201, 40, 40);
            redButton.setBorderPainted(true);
            currentPanel.add(redButton);
            redButton.setActionCommand("Color");
            redButton.addActionListener(this);
            map.put(toColor(COLORS[i]), COLORS[i]);
        }

    }

    private void createList(List<Cloth> clothes) {

        for (Cloth cloth : clothes) {
            JLabel item = new JLabel(cloth.getColor() + " " + cloth.getClothType() + " : " + cloth.getId());
            item.setBounds(1, 1 + clothes.indexOf(cloth) * 20, 500, 20);
            mainPanel.add(item);
        }
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

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

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand() == "Add") {
            colorMessage();
        } else if (event.getActionCommand() == "Remove") {
            idMessage("Remove");
        } else if (event.getActionCommand() == "Buy") {
            idMessage("Buy");
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
