package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Cloth;

public class RankingContainer extends Container {
    ClothGUI parent;
    private final Color COLOR = Color.white;
    JScrollPane scroll;
    JPanel mainPanel;
    public RankingContainer(ClothGUI parent){
        super();
        this.parent = parent;
        setLayout(null);
        mainPanel = initializePanel(new Rectangle(1, 1, parent.getWidth(), parent.getHeight() ));
        scroll = new JScrollPane(mainPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(1, 1, parent.getWidth() - 15, parent.getHeight() -15);
        add(scroll);
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
        if (message == "No clothes have been bought!") {
            JLabel test = new JLabel(message);
            test.setBackground(Color.gray);
            test.setBounds(1, 1, 500, 90);

            mainPanel.add(test);
        } else {
            createList(clothes);
        }

    }

       private void createList(List<Cloth> clothes) {

        for (Cloth cloth : clothes) {
            JLabel item = new JLabel(cloth.getColor() + " " + cloth.getClothType() + ", ID: " + cloth.getId()+", Purchase Count: "+cloth.getPurchaseCount());
            item.setBounds(1, 1 + clothes.indexOf(cloth) * 20, 500, 20);
            mainPanel.add(item);
        }
    }
}
