package AllGamer.StealthLogin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author AllGamer
 */
public class StealthLoginPlayerListener extends PlayerListener {
    private final StealthLogin plugin;

    public StealthLoginPlayerListener(StealthLogin instance) {
        plugin = instance;
    }

    //Insert Player related code here
}

