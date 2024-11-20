package ui;
import javax.swing.*;
public class ClothUI extends JFrame {

    private int width = 500;
    private int height = 500;
    private JMenuBar menuBar;
    public ClothUI(){
        super("Clothing Inventory");
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   
        createMenuBar();
        finish();
        


    }
    private void createMenuBar(){
        menuBar = new JMenuBar();
        JMenu fileJMenu = new JMenu("File");
        menuBar.add(fileJMenu);
        setJMenuBar(menuBar);
    }
    private void finish(){

        setResizable(true);
        setVisible(true);
    }
    public static void main(String args[]){
        new ClothUI();
    }
}
