package ui;

import java.awt.*;

import javax.swing.*;

//Represents the main tab of the UI
public class MenuPanel extends JPanel {
    private final Color mainColor = Color.white;
    JButton viewClothesButton;
    JButton viewSalesButton;
    JButton requestButton;
    ClothGUI parent;

    // EFFECTS: sets this.parent to parent; sets up the MenuPanel's visual
    // properties and creates a welcome message;
    public MenuPanel(ClothGUI parent) {
        super();
        this.parent = parent;
        setBackground(mainColor);
        JLabel welcomeMessage = new JLabel("Welcome to your inventory. Select one of the following options:");
        welcomeMessage.setForeground(Color.black);
        welcomeMessage.setBounds(1, 1, 600, 100);
        add(welcomeMessage);
        welcome();
        setLayout(null);
    }

    // MODIFIES: this
    // Sets up the panel; adds a view clothes button, view sales button and request
    // item button
    private void welcome() {
        viewClothesButton = new JButton("View the list of clothing available for purchase");
        viewClothesButton.setForeground(Color.white);
        viewClothesButton.setBackground(Color.gray);
        viewClothesButton.addActionListener(parent);
        viewClothesButton.setActionCommand("View List");
        viewClothesButton.setBounds(1, 103, 600, 100);
        add(viewClothesButton);
        viewSalesButton = new JButton("View the sales ranking of sold clothing");
        viewSalesButton.setForeground(Color.white);
        viewSalesButton.setBackground(Color.gray);
        viewSalesButton.addActionListener(parent);
        viewSalesButton.setActionCommand("View Ranking");
        viewSalesButton.setBounds(1, 206, 600, 100);
        add(viewSalesButton);

        requestButton = new JButton("Request Item");
        requestButton.setForeground(Color.white);
        requestButton.setBackground(Color.gray);
        requestButton.addActionListener(parent);
        requestButton.setActionCommand("Request");
        requestButton.setBounds(1, 312, 600, 100);
        add(requestButton);

    }

}
