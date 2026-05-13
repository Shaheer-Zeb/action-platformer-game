package Managers;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * @author ShaheerZK
 * Uses a Singleton pattern, because why the fuck not?!
 */

public class HealthManager
{
    private static BufferedImage sheet;
    private int spriteSize = 17;

    private Heart[] hearts;
    private static HealthManager instance;
    
    private int initialHealth = 5, health = initialHealth;
    private Image fullHeartImage, emptyHeartImage;
        
    private class Heart
    {
        private Image image;
        Heart (Image image)
        {
            this.image = image;
        }
        public Image getImage()
        {
            return image;
        }
        public void setImage(Image image)
        {
            this.image = image;
        }
    }   
    private HealthManager()
    {
        readSheet();
        initHearts();
    }
    public static HealthManager getInstance()
    {
        if (instance == null)
            instance = new HealthManager();
        return instance;
    }
    private void readSheet()
    {
        try 
        {
            sheet = ImageIO.read(HealthManager.class.getResourceAsStream("/Assets/Player/HeartSpritesheet.png"));
        } 
        catch (IOException e) 
        {
            System.out.println("Unable to open the heart spritesheet");
        }
    }
    public void drawHearts(Graphics2D g2d)
    {
        int xPos = Main.Main.WIDTH - 140; // here this constant is added just to give enough space for the five hearts to render
        int yPos = 20;
        int distanceBetweenHearts = 10;
        for (int i = 0; i < initialHealth; i++, xPos += spriteSize + distanceBetweenHearts)
        {
            g2d.drawImage(hearts[i].getImage(), xPos, yPos, spriteSize, spriteSize, null);
        }
    }
    public void reduceHealth()
    {
        if (health > 0)
            hearts[--health].setImage(emptyHeartImage);
    }
    private void initHearts()
    {
        hearts = new Heart[initialHealth];
        
        int spriteX = 0, spriteY = 0;
        fullHeartImage = sheet.getSubimage(spriteX, spriteY, spriteSize, spriteSize);
        spriteX += spriteSize * 4;
        emptyHeartImage = sheet.getSubimage(spriteX, spriteY, spriteSize, spriteSize);
        
        for (int i = 0; i < initialHealth; i++)
        {
            hearts[i] = new Heart(fullHeartImage);
        }
    }
    public int getHealth()
    {
        return health;
    }
}