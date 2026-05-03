package Main;

import Entity.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ShaheerZK
 */

public class GamePanel extends JPanel implements ActionListener
{
    private final int FPS = 60;
    private final Timer timer;
    private KeyManager keyManager = KeyManager.getInstance();
    private final Image background;
    
    private Player player;
    
    
    GamePanel()
    {
        background = new ImageIcon(getClass().getResource("/Assets/Backgrounds/background.jpg")).getImage();
        
        this.addKeyListener(keyManager);
        this.setFocusable(true);
        this.requestFocus();
                
        player = new Player();
        
        timer = new Timer(1000/ FPS, this);
        timer.start();
    }
    public void draw(Graphics2D g2)
    {
        player.draw(g2);
    }
    public void update()
    {
        player.update();
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
}