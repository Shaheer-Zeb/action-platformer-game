package Entity;

import Main.GamePanel;
import Managers.CollisionManager;
import Managers.HealthManager;
import Managers.KeyManager;
import Managers.SoundManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private final int speedX = 3;
    private double speedY;
    private boolean moving;
    
    private final int jumpSpeed = 12;
    private final double gravity = 0.5;
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
    
    private long attackSoundStartTime = System.currentTimeMillis();
    private final int SOUNDDELAY = 300;
    
    private int health = 5;
    private HealthManager healthManager = HealthManager.getInstance();
    private long lastHitTime = System.currentTimeMillis();
    private final int GETTINGHITDELAY = 2000;
    private boolean isAttacking;
    private int pushBackDistance = 30;
    private int pushBackSpeed = 2;
    private boolean isPushedBack;
    private int pushBackReduction = 1;
    
    private static Player instance;
    private Boss boss;
    private GamePanel panel;
    
    private enum SpriteAction
    {
        ATTACK1, ATTACK2, ATTACK3, DEAD, HURT, IDLE, JUMP, RUN;
    }
    
    private Player(int xPos, int yPos, int width, int height, Image image, GamePanel panel) 
    {
        super(xPos, yPos, width, height, image);
        loadSpriteSheet("/Assets/Player/PlayerSpritesheet.png");
        
        this.keyManager = Managers.KeyManager.getInstance();
        this.panel = panel;
        boss = this.panel.getBoss();
        
        spriteTimer = new Timer(spriteChangeDelay, this);
        spriteTimer.start();
    }
    public Player(GamePanel panel)
    {
        this(initialXPos, initialYPos, width, height, null, panel);
    }
//    public static Player getInstance(GamePanel panel)
//    {
//        if (instance == null)
//            instance = new Player(panel);
//        return instance;
//    }
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
        checkBeingAttacked();
    }
    private void changePosition()
    {
        manageMovement();
        manageJump();
        manageAttack();
    }
    private void manageMovement()
    {
        moving = false;
        if (keyManager.isLeftPressed())
        {
            facingLeft = true;
            if (!isJumping)
                spriteRowNumber = SpriteAction.RUN.ordinal();
            moving = true;
            changeXPos(-speedX);
            
//            long currentTime = System.currentTimeMillis();
//            long deltaTime = currentTime - attackSoundStartTime;
//            if (deltaTime > SOUNDDELAY)
//            {
//                SoundManager.playRandomWalkingSound();
//                attackSoundStartTime = currentTime;
//            }
        }
        else if (keyManager.isRightPressed())
        {
            facingLeft = false;
            if (!isJumping)
                spriteRowNumber = SpriteAction.RUN.ordinal();
            moving = true;
            changeXPos(speedX);
        }
        else if (!moving && !isJumping)
            spriteRowNumber = SpriteAction.IDLE.ordinal();
    }
    private void manageJump()
    {
        if (keyManager.isJumpPressed() && !isJumping)
        {
            spriteRowNumber = SpriteAction.JUMP.ordinal();
            isJumping = true;
            speedY = -jumpSpeed;
        }
        if (isJumping)
        {
            changeYPos((int)speedY);
            speedY += gravity;
            if (getYPos() >= initialYPos)
            {
                setYPos(initialYPos);
                isJumping = false;
                speedY = 0;
            }
        }
    }
    private void manageAttack()
    {
        if (keyManager.isAttack1Pressed())
        {
            SpriteAction[] actions = SpriteAction.values();
            int randomIndex = random.nextInt(0, 3);
            SpriteAction action = actions[randomIndex];
            long currentTime = System.currentTimeMillis();
            long deltaTime = currentTime - attackSoundStartTime;
            if (deltaTime > SOUNDDELAY)
            {
                SoundManager.playRandomAttackSound();
                attackSoundStartTime = currentTime;
            }
            spriteRowNumber = action.ordinal();
            isAttacking = true;
        }
    }
    private void checkBeingAttacked()
    {
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastHitTime;
        
        if (deltaTime > GETTINGHITDELAY && boss.getIsAttacking() && CollisionManager.playerAndBossColliding(this, boss) && !isJumping)
        {
            int distance = boss.isFacingRight() ? pushBackDistance : -pushBackDistance;
            pushBack(distance);
            healthManager.reduceHealth();
            lastHitTime = currentTime;
        }
    }
    private void pushBack(int distance)
    {
        int tempPosition = getXPos();
        boolean isRight = distance > 0;
        isPushedBack = true;
        int speedX = (distance > 0) ? pushBackSpeed : -pushBackSpeed;
        if (isPushedBack)
        {
            changeXPos(speedX);
            pushBackReduction = (distance > 0) ? -pushBackReduction : pushBackReduction;
            speedX += pushBackReduction;
            if (isRight && getXPos() < tempPosition - distance)
            {
                setXPos(tempPosition - distance);
                isPushedBack = false;
                speedX = 0;
            }
            else if (!isRight && getXPos() > tempPosition + distance)
            {
                setXPos(tempPosition + distance);
                isPushedBack = false;
                speedX = 0;
            }
        }
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
    public boolean getIsAttacking()
    {
        return isAttacking;
    }
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(getXPos(), getYPos(), width - 100, height); // this woodoo constant is used to just adjust the collision scale
    }
}
