package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Cloth;

//Represents the sales tab
public class RankingContainer extends Container {
    ClothGUI parent;
    private final Color mainColor = Color.white;
    JScrollPane scroll;
    JPanel mainPanel;

    // EFFECTS: sets this.parent to parent; sets up the mainPanel and scroll
    public RankingContainer(ClothGUI parent) {
        super();
        this.parent = parent;
        setLayout(null);
        mainPanel = initializePanel(new Rectangle(1, 1, parent.getWidth(), parent.getHeight()));
        scroll = new JScrollPane(mainPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(1, 1, parent.getWidth() - 15, parent.getHeight() - 15);
        add(scroll);
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
     * EFFECTS: updates the sales UI based on the list of clothes and message
     */
    public void update(String message, List<Cloth> clothes) {
        mainPanel.removeAll();
        if (message == "No clothes have been bought!") {
            JLabel test = new JLabel(message);
            test.setBackground(Color.gray);
            test.setBounds(1, 1, 500, 90);

            mainPanel.add(test);
        } else {
            createList(clothes);
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a list of JLabels, based on the clothes, that is added to
    // the mainPanel
    private void createList(List<Cloth> clothes) {
        for (Cloth cloth : clothes) {
            JLabel item = new JLabel(cloth.getColor() + " " + cloth.getClothType() + ", ID: " + cloth.getId()
                    + ", Purchase Count: " + cloth.getPurchaseCount());
            item.setBounds(1, 1 + clothes.indexOf(cloth) * 20, 500, 20);
            mainPanel.add(item);
        }
    }
}
