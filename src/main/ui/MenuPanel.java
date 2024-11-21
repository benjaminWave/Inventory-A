package ui;

import java.awt.*;

import javax.swing.*;

public class MenuPanel extends JPanel {
    private final Color COLOR = Color.white;
    JButton viewClothesButton;
    JButton viewSalesButton;
    JButton requestButton;
    ClothUI parent;

    public MenuPanel(ClothUI parent) {
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
        viewSalesButton.setActionCommand("View Ranking");
        viewSalesButton.setBounds(1, 103, 600, 100);
        add(viewSalesButton);
    }

}
