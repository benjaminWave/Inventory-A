package ui;

import java.awt.*;

import javax.swing.*;

public class MenuPanel extends JPanel {
    private final Color COLOR = Color.white;
    JButton viewClothesButton;
    JButton viewSalesButton;
    JButton requestButton;
    ClothGUI parent;

    public MenuPanel(ClothGUI parent) {
        super();
        this.parent = parent;
        setBackground(COLOR);
        welcome();
        setLayout(null);
    }

    private void welcome() {

        JLabel welcomeMessage = new JLabel("Welcome to your inventory. Select one of the following options:");
        welcomeMessage.setForeground(Color.black);
        add(welcomeMessage);
        viewClothesButton = new JButton("View the list of clothing available for purchase");
        viewClothesButton.setForeground(Color.white);
        viewClothesButton.setBackground(Color.gray);
        viewClothesButton.addActionListener(parent);
        viewClothesButton.setActionCommand("View List");
        viewClothesButton.setBounds(1, 1, 600, 100);
        add(viewClothesButton);
        viewSalesButton = new JButton("View the sales ranking of sold clothing");
        viewSalesButton.setForeground(Color.white);
        viewSalesButton.setBackground(Color.gray);
        viewSalesButton.addActionListener(parent);
        viewSalesButton.setActionCommand("View Ranking");
        viewSalesButton.setBounds(1, 103, 600, 100);
        add(viewSalesButton);

        requestButton = new JButton("Request Item");
        requestButton.setForeground(Color.white);
        requestButton.setBackground(Color.gray);
        requestButton.addActionListener(parent);
        requestButton.setActionCommand("Request");
        requestButton.setBounds(1, 206, 600, 100);
        add(requestButton);


    }

}
