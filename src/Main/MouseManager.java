package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author ShaheerZK
 * Uses the Singleton pattern.
 */

public class MouseManager implements MouseListener
{
    private MouseManager(){}
    
    static MouseManager instance;
    public static MouseManager getInstance()
    {
        if (instance == null)
            instance = new MouseManager();
        return instance;
    }
    @Override
    public void mouseClicked(MouseEvent me) 
    {
    }
    @Override
    public void mousePressed(MouseEvent me) 
    {
    }
    @Override
    public void mouseReleased(MouseEvent me) 
    {
    }
    @Override
    public void mouseEntered(MouseEvent me) 
    {
    }
    @Override
    public void mouseExited(MouseEvent me) 
    {
    }
}