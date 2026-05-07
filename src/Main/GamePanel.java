package Main;

import Entity.Boss;
import Entity.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

/**
 * @author ShaheerZK
 * Currently uses a Singleton pattern, but I don't know if I'd need it.
 */

public class GamePanel extends JPanel implements ActionListener
{
    private final int FPS = 120;
    private final Timer timer;
    private KeyManager keyManager = KeyManager.getInstance();
    private final Image background;
    
    private Player player;
    private Boss boss;
    private static GamePanel instance;
    
    private GamePanel()
    {        
        this.addKeyListener(keyManager);
        this.setFocusable(true);
        this.requestFocus();
                
        player = Player.getInstance();
        boss = Boss.getInstance();
        
        background = LevelManager.loadLevel();
        timer = new Timer(1000/ FPS, this);
        timer.start();
    }
    public static GamePanel getInstance()
    {
        if (instance == null)
            instance = new GamePanel();
        return instance;
    }
    public void draw(Graphics2D g2)
    {
        displayLevelAndHealth(g2);
        player.draw(g2);
        boss.draw(g2);
    }
    public void update()
    {
        player.update();
        boss.update();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        draw(g2);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        update();
        repaint();
    }
    private void displayLevelAndHealth(Graphics2D g2)
    {
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g2.drawString("Level: " + LevelManager.getCurrentLevel(), 30, 30);
    }
}