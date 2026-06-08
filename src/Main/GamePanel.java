package Main;

import Managers.LevelManager;
import Managers.KeyManager;
import Entity.Boss;
import Entity.Player;
import Managers.HealthManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ShaheerZK
 */

public class GamePanel extends JPanel implements ActionListener
{
    private final int FPS = 90;
    private final Timer timer;
    private KeyManager keyManager = KeyManager.getInstance();
    private Image background;
    
    private Player player;
    private Boss boss;
    private HealthManager healthManager;
    private boolean isGameOver;
    
    private static GamePanel instance;
    
    public GamePanel()
    {        
        this.addKeyListener(keyManager);
        this.setFocusable(true);
        this.requestFocus();
        
        boss = new Boss(this);
        player = new Player(this);
        healthManager = HealthManager.getInstance();
        
        background = LevelManager.loadLevel();
        timer = new Timer(1000/ FPS, this);
        timer.start();
    }
//    public static GamePanel getInstance()
//    {
//        if (instance == null)
//            instance = new GamePanel();
//        return instance;
//    }
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
        checkGameOver();
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
        healthManager.drawHearts(g2);
        
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g2.drawString("Level: " + LevelManager.getCurrentLevel(), 30, 30);
    }
    private void checkGameOver()
    {
        if (healthManager.getHealth() <= 0)
            isGameOver = true;
        if (isGameOver)
        {
            System.out.println("Game over mate.");
            System.exit(0);
        }
    }
    public void changeLevel() {
        LevelManager.increaseLevel(); 
        background = LevelManager.loadLevel();
        
        player.resetPlayer();
        boss = new Boss(this);
        repaint();
    }
    public Boss getBoss()
    {
        return boss;
    }
    public Player getPlayer()
    {
        return player;
    }
}