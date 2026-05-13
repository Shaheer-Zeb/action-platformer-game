package Managers;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 * @author ShaheerZK
 */

public class LevelManager 
{
    private static int currentLevel;

    static 
    {
        try (InputStream is = LevelManager.class.getResourceAsStream("CurrentLevel.txt")) {
            
            if (is != null) 
            {
                Scanner scanner = new Scanner(is);
                if (scanner.hasNextInt()) 
                {
                    currentLevel = scanner.nextInt() % 9;
                }
                scanner.close();
            } 
            else 
            {
                System.err.println("File not found: /CurrentLevel.txt");
            }
        } 
        catch (IOException ex) 
        {
            System.getLogger(LevelManager.class.getName()).log(System.Logger.Level.ERROR, "Failed to read level", ex);
        }
    }

    public static Image loadLevel() 
    {
        switch (currentLevel)
        {
            case 1 ->
            {
                SoundManager.playLevelOneMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level1.jpg")).getImage();
            }
            case 2 ->
            {
                SoundManager.playLevelTwoMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level2.jpg")).getImage();
            }
            case 3 ->
            {
                SoundManager.playLevelThreeMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level3.jpg")).getImage();
            }
            case 4 ->
            {
                SoundManager.playLevelFourMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level3.jpg")).getImage();
            }
            case 5 ->
            {
                SoundManager.playLevelFiveMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level1.jpg")).getImage();
            }
            case 6 ->
            {
                SoundManager.playLevelSixMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level1.jpg")).getImage();
            }
            case 7 ->
            {
                SoundManager.playLevelSevenMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level1.jpg")).getImage();
            }
            case 8 ->
            {
                SoundManager.playLevelEightMusic();
                return new ImageIcon(LevelManager.class.getResource("/Assets/Backgrounds/Level1.jpg")).getImage();
            }
        }
        return null;
    }
    public static int getCurrentLevel()
    {
        return currentLevel;
    }
}
