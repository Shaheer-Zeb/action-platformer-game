package Main;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author ShaheerZK
 */

public class Main
{
    private static JFrame frame;
    public static int WIDTH = 1200;
    public static int HEIGHT = 600;
    
    public static void main(String[] args) 
    {
        frame = new JFrame("Platformer");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        GamePanel panel = new GamePanel();
        frame.add(panel);
        
        frame.setVisible(true);
    }
}