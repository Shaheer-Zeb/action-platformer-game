package Managers;

import Entity.Boss;
import Entity.Player;
import java.awt.Rectangle;

/**
 * @author ShaheerZK
 */

public class CollisionManager
{
    private static Player player = Player.getInstance();
    private static Boss boss = Boss.getInstance();
    
    public static boolean playerAndBossColliding()
    {
        Rectangle playerRect = player.getBounds();
        Rectangle bossRect = boss.getBounds();
        return playerRect.intersects(bossRect);
    }
}