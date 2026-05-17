package Managers;

import Entity.Boss;
import Entity.Player;
import java.awt.Rectangle;

/**
 * @author ShaheerZK
 */
public class CollisionManager {

    public static boolean playerAndBossColliding(Player player, Boss boss) 
    {
        if (player == null || boss == null) 
        {
            return false;
        }

        Rectangle playerRect = player.getBounds();
        Rectangle bossRect = boss.getBounds();
        
        return playerRect != null && bossRect != null && playerRect.intersects(bossRect);
    }
}
