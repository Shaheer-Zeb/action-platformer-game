package Managers;

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
    private static File levelTwoMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level2.wav").getFile());
    private static File levelThreeMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level3.wav").getFile());
    private static File levelFourMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level4.wav").getFile());
    private static File levelFiveMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level5.wav").getFile());
    private static File levelSixMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level6.wav").getFile());
    private static File levelSevenMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level7.wav").getFile());
    private static File levelEightMusic = new File(SoundManager.class.getResource("/Sounds/Music/Level8.wav").getFile());
    private static Clip currentMusicClip;

    
    private static File attackSound1 = new File(SoundManager.class.getResource("/Sounds/Player/Attack1.wav").getFile());
    private static File attackSound2 = new File(SoundManager.class.getResource("/Sounds/Player/Attack2.wav").getFile());
    private static File attackSound3 = new File(SoundManager.class.getResource("/Sounds/Player/Attack3.wav").getFile());
    
    private static File[] attackSounds = new File[3];
    
    private static File walkingOne = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-001.ogg").getFile());
    private static File walkingTwo = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-002.ogg").getFile());
    private static File walkingThree = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-003.ogg").getFile());
    private static File walkingFour = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-004.ogg").getFile());
    private static File walkingFive = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-005.ogg").getFile());
    private static File walkingSix = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-006.ogg").getFile());
    private static File walkingSeven = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-007.ogg").getFile());
    private static File walkingEight = new File(SoundManager.class.getResource("/Sounds/Player/Walking/Steps_carpet-008.ogg").getFile());

    private static File[] walkingSounds = new File[8];
    static
    {
        attackSounds[0] = attackSound1;
        attackSounds[1] = attackSound2;
        attackSounds[2] = attackSound3;
        
        walkingSounds[0] = walkingOne;
        walkingSounds[1] = walkingTwo;
        walkingSounds[2] = walkingThree;
        walkingSounds[3] = walkingFour;
        walkingSounds[4] = walkingFive;
        walkingSounds[5] = walkingSix;
        walkingSounds[6] = walkingSeven;
        walkingSounds[7] = walkingEight;
        
        initCurrentClip();
    }
    public static void playLevelOneMusic()
    {
        if (levelOneMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                AudioInputStream levelOneMusicStream = AudioSystem.getAudioInputStream(levelOneMusic);
                Clip levelOneClip = AudioSystem.getClip();
                levelOneClip.open(levelOneMusicStream);
                levelOneClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelOneClip.start();
                currentMusicClip = levelOneClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelTwoMusic()
    {
        if (levelTwoMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelTowMusicStream = AudioSystem.getAudioInputStream(levelTwoMusic);
                Clip levelTwoClip = AudioSystem.getClip();
                levelTwoClip.open(levelTowMusicStream);
                levelTwoClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelTwoClip.start();
                
                currentMusicClip = levelTwoClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelThreeMusic()
    {
        if (levelThreeMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelThreeMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelFourMusic()
    {
        if (levelFourMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelFourMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelFiveMusic()
    {
        if (levelFiveMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelFiveMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelSixMusic()
    {
        if (levelSixMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelSixMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelSevenMusic()
    {
        if (levelSevenMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelSevenMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
    public static void playLevelEightMusic()
    {
        if (levelEightMusic.exists())
        {
            try
            {
                currentMusicClip.stop();
                
                AudioInputStream levelMusicStream = AudioSystem.getAudioInputStream(levelEightMusic);
                Clip levelClip = AudioSystem.getClip();
                levelClip.open(levelMusicStream);
                levelClip.loop(Clip.LOOP_CONTINUOUSLY);
                levelClip.start();
                
                currentMusicClip = levelClip;
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
                AudioInputStream attackStream = AudioSystem.getAudioInputStream(attackSounds[randomIndex]);
                Clip attackClip = AudioSystem.getClip();
                attackClip.open(attackStream);
                attackClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the attack sound mate.");
            }
        }
    }
    public static void playRandomWalkingSound()
    {
        int randomIndex = random.nextInt(0, 8);
        if (walkingSounds[randomIndex].exists())
        {
            try
            {
                AudioInputStream walkStream = AudioSystem.getAudioInputStream(walkingSounds[randomIndex]);
                Clip walkClip = AudioSystem.getClip();
                walkClip.open(walkStream);
                walkClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the walking sound mate.");
            }
        }
    }
    public static void initCurrentClip()
    {
         if (levelOneMusic.exists())
        {
            try
            {
                AudioInputStream levelOneMusicStream = AudioSystem.getAudioInputStream(levelOneMusic);
                Clip levelOneClip = AudioSystem.getClip();
                levelOneClip.open(levelOneMusicStream);
                levelOneClip.loop(Clip.LOOP_CONTINUOUSLY);
                
                currentMusicClip = levelOneClip;
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the level one music mate.");
            }
        }
    }
}