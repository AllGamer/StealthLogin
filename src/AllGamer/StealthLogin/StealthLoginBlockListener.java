package AllGamer.StealthLogin;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * StealthLogin block listener
 * @author AllGamer
 */
public class StealthLoginBlockListener extends BlockListener {
    private final StealthLogin plugin;

    public StealthLoginBlockListener(final StealthLogin plugin) {
        this.plugin = plugin;
    }

    //put all Block related code here
}
