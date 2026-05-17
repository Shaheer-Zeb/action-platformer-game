package Managers;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 * @author ShaheerZK
 */
public class LevelManager 
{
    private static int currentLevel = 1;
    private static final int MAX_LEVELS = 8;
    private static final Path SAVE_PATH = Paths.get("CurrentLevel.txt");

    static 
    {
        loadLevelFromDisk();
    }

    private static void loadLevelFromDisk() 
    {
        try 
        {
            if (Files.exists(SAVE_PATH)) 
            {
                String content = Files.readString(SAVE_PATH).trim();
                currentLevel = Integer.parseInt(content);
                currentLevel = Math.max(1, Math.min(currentLevel, MAX_LEVELS));
            }
        } 
        catch (IOException | NumberFormatException ex) 
        {
            currentLevel = 1;
        }
    }

    public static Image loadLevel() 
    {
        switch (currentLevel) 
        {
            case 1 -> { SoundManager.playLevelOneMusic(); return getImage("Level1.jpg"); }
            case 2 -> { SoundManager.playLevelTwoMusic(); return getImage("Level2.jpg"); }
            case 3 -> { SoundManager.playLevelThreeMusic(); return getImage("Level3.jpg"); }
            case 4 -> { SoundManager.playLevelFourMusic(); return getImage("Level3.jpg"); } 
            case 5 -> { SoundManager.playLevelFiveMusic(); return getImage("Level1.jpg"); }
            case 6 -> { SoundManager.playLevelSixMusic(); return getImage("Level1.jpg"); }
            case 7 -> { SoundManager.playLevelSevenMusic(); return getImage("Level1.jpg"); }
            case 8 -> { SoundManager.playLevelEightMusic(); return getImage("Level1.jpg"); }
            default -> { return getImage("Level1.jpg"); }
        }
    }
    private static Image getImage(String fileName) 
    {
        String fullPath = "/Assets/Backgrounds/" + fileName;
        var resource = LevelManager.class.getResource(fullPath);
        if (resource == null) 
        {
            System.err.println("Resource not found: " + fullPath);
            return null;
        }
        return new ImageIcon(resource).getImage();
    }

    public static void increaseLevel() 
    {
        if (currentLevel < MAX_LEVELS) 
        {
            currentLevel++;
            saveLevelToDisk();
        }
    }

    public static void resetLevel() 
    {
        currentLevel = 1;
        saveLevelToDisk();
    }

    private static void saveLevelToDisk() 
    {
        try 
        {
            Files.writeString(SAVE_PATH, String.valueOf(currentLevel));
        } 
        catch (IOException ex)
        {
            System.err.println("Failed to save level mate.: " + ex.getMessage());
        }
    }

    public static int getCurrentLevel() 
    {
        return currentLevel;
    }
}
