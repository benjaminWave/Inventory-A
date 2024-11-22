package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RequestContainer extends Container implements ActionListener {
    JPanel mainPanel;
    ClothGUI parent;
    JTextField field;
    JLabel confimation;
    private final Color COLOR = Color.white;

    public RequestContainer(ClothGUI parent) {
        super();
        this.parent = parent;
        setLayout(null);
        mainPanel = initializePanel(new Rectangle(1, 1, parent.getWidth(), parent.getHeight()));
        confimation = new JLabel();
        confimation.setBounds(1,130,500,100);
        mainPanel.add(confimation);
        add(mainPanel);
        idMessage();

    }

    private JPanel initializePanel(Rectangle rect) {
        JPanel referencePanel = new JPanel();
        referencePanel.setBackground(COLOR);
        referencePanel.setLayout(null);
        referencePanel.setBounds(rect);
        return referencePanel;
    }

    private void idMessage() {
     
        JLabel select = new JLabel("Enter the 4 digit id of item: ");
        select.setBackground(Color.gray);
        select.setBounds(1, 1, 500, 100);
        mainPanel.add(select);
        createId();

        
    }
    private void createId() {
        field = new JTextField();
        field.setBounds(1, 100, 100, 20);
        mainPanel.add(field);

        JButton enter = new JButton();
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\user\\Documents\\CPSC210 folder\\0000\\ProjectStarter\\data\\images\\enter.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(60, 20, 0);
        icon = new ImageIcon(image);
        enter.setIcon(icon);
        enter.setBounds(101, 100, 60, 20);
        mainPanel.add(enter);
        enter.addActionListener(this);
        String cmd = "Request";
        enter.setActionCommand(cmd);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand()=="Request"){
            int enteredId = Integer.parseInt(field.getText());
            if (parent.getHandler().handleRequest(enteredId)== true){
                confimation.setText("Successfully added item " +enteredId+" to Request List!");
            }else{
                confimation.setText("Item "+ enteredId+ " is already in the inventory!");
            }
           field.setText(""); 
        }
    }


}
