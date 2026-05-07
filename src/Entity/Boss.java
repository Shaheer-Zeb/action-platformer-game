package Entity;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * @author ShaheerZK
 * Uses a Singleton pattern of course, because what else.
 */

public class Boss extends Entity implements ActionListener
{
    private static int initialX = Main.Main.WIDTH / 2 + 100;
    private static int initialY = Main.Main.HEIGHT / 2 - 50; // Again adding a ghost constant here just to align to the ground.
    private static int width = 500;
    private static int height = 300;
    private final int speed = 1;
    
    private final int spriteXSize = 288;
    private final int spriteYSize = 160;
    private int sheetX, sheetY;
    private BufferedImage sheet;
    private Timer spriteTimer;
    private int spriteChangeDelay = 100;
    private int spriteRowNumber = SpriteAction.IDLE.ordinal();
    private int spriteColumnNumber;

    private boolean facingRight;
    
    
    private static Boss instance;
    private enum SpriteAction
    {
        IDLE, WALK, ATTACK;
    }
    private Boss()
    {
        super(initialX, initialY, width, height, null);
        loadSpriteSheet("/Assets/Boss/Boss1.png");
        
        spriteTimer = new Timer(spriteChangeDelay, this);
        spriteTimer.start();
    }
    public static Boss getInstance()
    {
        if (instance == null)
            instance = new Boss();
        return instance;
    }
    public void update()
    {
        move();
    }
    private void move()
    {
        Player player = Player.getInstance();
        int playerX = player.getXPos();
        
        if (playerX < this.getXPos())
        {
            spriteRowNumber = SpriteAction.WALK.ordinal();
            this.changeXPos(-speed);
            facingRight = false;
        }
        else if (playerX > this.getXPos())
        {
            spriteRowNumber = SpriteAction.WALK.ordinal();
            this.changeXPos(speed);
            facingRight = true;
        }
    }
    public void draw(Graphics2D g2d)
    {
        BufferedImage sprite = sheet.getSubimage(sheetX, sheetY, spriteXSize, spriteYSize);
        drawImage(g2d, sprite, this.getXPos(), this.getYPos(), facingRight);
    }
    private void drawImage(Graphics2D g2d, BufferedImage sprite, int x, int y, boolean facingLeft)
    {
        if (facingLeft)
            g2d.drawImage(sprite, getXPos() + getWidth(), this.getYPos(), -getWidth(), getHeight(), null);
        else
            g2d.drawImage(sprite, getXPos(), getYPos(), getWidth(), getHeight(), null);
    }
    private void loadSpriteSheet(String path)
    {
        try 
        {
            sheet = ImageIO.read(getClass().getResourceAsStream(path));
        } 
        catch (IOException e) 
        {
            System.out.println("Unable to open the player spritesheet");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        sheetX += spriteXSize;
        sheetY = spriteRowNumber * spriteYSize;
        
        switch (spriteRowNumber)
        {
            case 0 -> sheetX = sheetX > 15 * spriteXSize ? 0 : sheetX;
            case 1 -> sheetX = sheetX > 11 * spriteXSize ? 0 : sheetX;
            case 2 -> sheetX = sheetX > 15 * spriteXSize ? 0 : sheetX;
        }
    }
}