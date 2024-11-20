package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClothUI extends JFrame implements ActionListener {

    private int width = 500;
    private int height = 500;
    private JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem saveFile;
    JMenuItem loadFile;

    public ClothUI() {
        super("Clothing Inventory");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createMenuBar();
        finish();

    }

    private void createMenuBar() {
        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.saveFile = new JMenuItem("Save");
        this.loadFile = new JMenuItem("Load");
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        saveFile.addActionListener(this);
        loadFile.addActionListener(this);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void finish() {

        setResizable(true);
        setVisible(true);
    }

    private JPopupMenu questionUser(String message) {
        JPopupMenu query = new JPopupMenu();

        JMenuItem yes = new JMenuItem("Yes");
        yes.addActionListener(this);
        JMenuItem no = new JMenuItem("No");
        no.addActionListener(this);
        JLabel title = new JLabel("Are you sure you want to " + message + " ?");
        query.add(title);
        query.add(yes);
        query.add(no);

        return query;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == saveFile) {
            JPopupMenu query = questionUser("save");
            query.show(this, 50, 50);

        } else if (source == loadFile) {
            JPopupMenu query = questionUser("load");
            query.show(this, 50, 50);
        } else if (source.getClass() == new JMenuItem().getClass()) {
            JMenuItem item = (JMenuItem) source;
            if (item.getText() == "Yes") {
                System.out.print("yes");
            }
        }
    }

    public static void main(String args[]) {
        new ClothUI();
    }
}
