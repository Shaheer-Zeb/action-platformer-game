package Main;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Random;

/**
 * @author ShaheerZK
 */

public class SoundManager
{
    private static Random random = new Random();
    private SoundManager(){}
    
    private static File levelOneMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level1.wav").getFile());
    
    private static File attackSound1 = new File(SoundManager.class.getResource("/Sounds/Player/Attack1.wav").getFile());
    private static File attackSound2 = new File(SoundManager.class.getResource("/Sounds/Player/Attack2.wav").getFile());
    private static File attackSound3 = new File(SoundManager.class.getResource("/Sounds/Player/Attack3.wav").getFile());
    
    private static File[] attackSounds = new File[3];
    static
    {
        attackSounds[0] = attackSound1;
        attackSounds[1] = attackSound2;
        attackSounds[2] = attackSound3;
    }
    
    
    public static void playLevelOneMusic()
    {
        if (levelOneMusic.exists())
        {
            try
            {
                AudioInputStream levelOneMusicStream = AudioSystem.getAudioInputStream(levelOneMusic);
                Clip levelOneClip = AudioSystem.getClip();
                levelOneClip.open(levelOneMusicStream);
                levelOneClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelOneClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playRandomAttackSound()
    {
        int randomIndex = random.nextInt(0, 3);
        if (attackSounds[randomIndex].exists())
        {
            try
            {
                AudioInputStream levelOneMusicStream = AudioSystem.getAudioInputStream(attackSounds[randomIndex]);
                Clip attackClip = AudioSystem.getClip();
                attackClip.open(levelOneMusicStream);
                attackClip.loop(0);
                attackClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the attack sound mate.");
            }
        }
    }
}