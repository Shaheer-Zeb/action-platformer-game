package Entity;

import Main.KeyManager;
import Main.SoundManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author ShaheerZK
 */
public class Player extends Entity implements ActionListener
{
    private final int speed = 3;
    private final int jumpSpeed = -100;
    private final int gravity = 3;
    private boolean facingLeft;
    private boolean isJumping;
    
    private Timer spriteTimer;
    private BufferedImage sheet;
    private final int spriteSize = 128;
    
    private int sheetX;
    private int sheetY;
    
    private int spriteRowNumber = SpriteAction.IDLE.ordinal();
    private int spriteColumnNumber = 1;
    private int spriteChangeDelay = 100;
    
    private static final int initialXPos = Main.Main.WIDTH / 7;
    private static final int initialYPos = Main.Main.HEIGHT / 2 + 70; //  Adding a ghost constant, just to adjust the yPos of player
    private static final int width = 150;
    private static final int height = 150;
    
    private final KeyManager keyManager;
    private Random random = new Random();
    
    private enum SpriteAction
    {
        ATTACK1, ATTACK2, ATTACK3, DEAD, HURT, IDLE, JUMP, RUN;
    }
    
    private Player(int xPos, int yPos, int width, int height, Image image) 
    {
        super(xPos, yPos, width, height, image);
        loadSpriteSheet("/Assets/Player/PlayerSpritesheet.png");
        
        this.keyManager = Main.KeyManager.getInstance();
        
        spriteTimer = new Timer(spriteChangeDelay, this);
        spriteTimer.start();
    }
    public Player()
    {
        this(initialXPos, initialYPos, width, height, null);
    }
    public void draw(Graphics2D g2d)
    {
        BufferedImage sprite = sheet.getSubimage(sheetX, sheetY, spriteSize, spriteSize);
        drawImage(g2d, sprite, this.getXPos(), this.getYPos(), facingLeft);
    }
    private void drawImage(Graphics2D g2d, BufferedImage sprite, int x, int y, boolean facingLeft)
    {
        if (facingLeft)
            g2d.drawImage(sprite, getXPos() + getWidth(), this.getYPos(), -getWidth(), getHeight(), null);
        else
            g2d.drawImage(sprite, getXPos(), getYPos(), getWidth(), getHeight(), null);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        sheetX += spriteSize;
        sheetY = spriteRowNumber * spriteSize;
        
        switch (spriteRowNumber) 
        {
            case 0 -> sheetX = (sheetX > 5 * spriteSize) ? 0 : sheetX;
            case 1 -> sheetX = (sheetX > 3 * spriteSize) ? 0 : sheetX;
            case 2 -> sheetX = (sheetX > 2 * spriteSize) ? 0 : sheetX;
            case 3 -> sheetX = (sheetX > 2 * spriteSize) ? 0 : sheetX;
            case 4 -> sheetX = (sheetX > 1 * spriteSize) ? 0 : sheetX;
            case 5 -> sheetX = (sheetX > 5 * spriteSize) ? 0 : sheetX;
            case 6 -> sheetX = (sheetX > 11 * spriteSize) ? 0 : sheetX;
            case 7 -> sheetX = (sheetX > 7 * spriteSize) ? 0 : sheetX;
        }
    }
    public void update()
    {
        changePosition();
        if (isJumping)
            applyGravity();
    }
    private void changePosition()
    {
        boolean moving = false;
        if (keyManager.isLeftPressed())
        {
            facingLeft = true;
            spriteRowNumber = SpriteAction.RUN.ordinal();
            moving = true;
            changeXPos(-speed);
        }
        else if (keyManager.isRightPressed())
        {
            facingLeft = false;
            spriteRowNumber = SpriteAction.RUN.ordinal();
            moving = true;
            changeXPos(speed);
        }
        if (keyManager.isJumpPressed() && !isJumping)
        {
            spriteRowNumber = SpriteAction.JUMP.ordinal();
            isJumping = true;
            changeYPos(jumpSpeed);
        }
        else if (keyManager.isAttack1Pressed())
        {
            SpriteAction[] actions = SpriteAction.values();
            int randomIndex = random.nextInt(0, 3);
            SpriteAction action = actions[randomIndex];
            
            SoundManager.playRandomAttackSound();
            
            spriteRowNumber = action.ordinal();
        }
        else if (!moving && !isJumping)
            spriteRowNumber = SpriteAction.IDLE.ordinal();
    }
    private void applyGravity()
    {
        if (getYPos() >= initialYPos)
            isJumping = false;
        changeYPos(gravity);
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
}
