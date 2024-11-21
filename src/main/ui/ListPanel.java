package ui;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class ListPanel extends JPanel {
    private final Color COLOR = Color.white;
    private JList list;
    List<String> thisList = new ArrayList<>();
 private JButton addButton;
 private ClothUI parent;
    public ListPanel(ClothUI parent){
        setBackground(COLOR);
        createAdd();
        list = new JList<>();
        this.parent = parent;
        //list.setListData(thisList.toArray());
        add(list);
   
    }
    public void create(String message){
        JLabel test = new JLabel(message);
        test.setBackground(Color.gray);
        add(test);

    }

    public void createAdd(){
        addButton = new JButton("View the list of clothing available for purchase");
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.gray);
        addButton.addActionListener(parent);
        addButton.setActionCommand("Add");
        add(addButton);
    }

    
}
