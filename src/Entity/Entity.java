package Entity;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * @author ShaheerZK
 */

public class Entity
{
    private int xPos, yPos, width, height;
    private Image image;
    public Entity(int xPos, int yPos, int width, int height, Image image)
    {
        setXPos(xPos);
        setYPos(yPos);
        setWidth(width);
        setHeight(height);
        setImage(image);
    }
    public void setXPos(int xPos)
    {
        this.xPos = (xPos >= 0) ? xPos : 0;
    }
    public void setYPos(int yPos)
    {
        this.yPos = (yPos >= 0) ? yPos : 0;
    }
    public void setImage(Image image)
    {
        this.image = (image != null) ? image : null;
    }
    public void setWidth(int width)
    {
        this.width = (width >= 0) ? width : 0;
    }
    public void setHeight(int height)
    {
        this.height = (height >= 0) ? height : 0;
    }
    public void changeXPos(int i)
    {
        xPos += i;
    }
    public void changeYPos(int i)
    {
        yPos += i;
    }
    public int getXPos()
    {
        return xPos;
    }
    public int getYPos()
    {
        return yPos;
    }
    public Image getImage()
    {
        return image;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Rectangle getBounds()
    {
        return new Rectangle(xPos, yPos, width, height);
    }
}