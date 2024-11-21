package ui;


import java.awt.*;

import javax.swing.*;


public class MenuPanel extends JPanel {
    private final Color COLOR = Color.white;
    private int scaleFactor = 8;
    JButton viewClothesButton;
    JButton viewSalesButton;
    JButton requestButton;
    ClothUI parent;
    public MenuPanel(ClothUI parent) {
        super();
        this.parent = parent;
        // setBorder(BorderFactory.createEmptyBorder(400,500,0,0));
        setBackground(COLOR);
        welcome();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        add(viewClothesButton);
        viewSalesButton = new JButton("View the sales ranking of sold clothing");
        viewSalesButton.setForeground(Color.white);
        viewSalesButton.setBackground(Color.gray);

        add(viewSalesButton);
        /*System.out.println("type 'q' to view the list of clothing available for purchase");
        System.out.println("type 'e' to view the sales ranking of sold clothing");
        System.out.println("type 'a' to add a cloth item");
        System.out.println("type 'r' to request a cloth item");
        System.out.println("type 's' to save this inventory");
        System.out.println("type 'l' to load a inventory");
        System.out.println("type 'x' to exit");*/
    }


}
