package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author ShaheerZK
 * Uses the Singleton pattern.
 */

public class KeyManager implements KeyListener
{
    private KeyManager(){}
    private static KeyManager instance;
    public static KeyManager getInstance()
    {
        if (instance == null)
        {
            instance = new KeyManager();
        }
        return instance;
    }
    private boolean leftPressed, rightPressed, jumpPressed, jPressed, isAlreadyAttacking;
    @Override
    public void keyTyped(KeyEvent ke) 
    {
    }
    @Override
    public void keyPressed(KeyEvent ke) 
    {
        if (ke.getKeyChar() == 'a')
            leftPressed = true;
        else if (ke.getKeyChar() == 'd')
            rightPressed = true;
        if (ke.getKeyChar() == ' ')
            jumpPressed = true;
        if (ke.getKeyChar() == 'j')
        {
            if (!isAlreadyAttacking)
            {
                jPressed = true;
                isAlreadyAttacking = true;
            }
            else
                jPressed = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        if (ke.getKeyChar() == 'a')
            leftPressed = false;
        else if (ke.getKeyChar() == 'd')
            rightPressed = false;
        if (ke.getKeyChar() == ' ')
            jumpPressed = false;
        if (ke.getKeyChar() == 'j')
        {
            jPressed = false;
            isAlreadyAttacking = false; 
        }
    }
    public boolean isLeftPressed()
    {
        return leftPressed;
    }
    public boolean isRightPressed()
    {
        return rightPressed;
    }
    public boolean isJumpPressed()
    {
        return jumpPressed;
    }
    public boolean isAttack1Pressed()
    {
        return jPressed;
    }
}