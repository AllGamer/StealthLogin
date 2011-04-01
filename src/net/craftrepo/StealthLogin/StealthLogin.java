package net.craftrepo.StealthLogin;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * CraftRepo StealthLogin for Bukkit
 * @author AllGamer
 * 
 * Copyright 2011 AllGamer, LLC.
 * See LICENSE for licensing information.
 */

@SuppressWarnings("unused")
public class StealthLogin extends JavaPlugin 
{
    private final StealthLoginPlayerListener playerListener = new StealthLoginPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public final static Logger log = Logger.getLogger("Minecraft");
	public static String logPrefix = "[StealthLogin]";
	public static PermissionHandler Permissions = null;

    public void onEnable() 
    {
		PluginManager pm = getServer().getPluginManager();
		setupPermissions();
		registerListeners();
		log.info(logPrefix + " version " + getDescription().getVersion() + " enabled!");
    }
    
    public void onDisable() 
    {
    	log.info(logPrefix + " version " + getDescription().getVersion() + " disabled!");
    }
    
    public void registerListeners() 
	{
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
	}
    
    public void setupPermissions() 
	{
		Plugin stealthlogin = this.getServer().getPluginManager().getPlugin("Permissions");
		PluginDescriptionFile pdfFile = this.getDescription();

		if (StealthLogin.Permissions == null) 
		{
			if (stealthlogin != null)
			{
				this.getServer().getPluginManager().enablePlugin(stealthlogin);
				StealthLogin.Permissions = ((Permissions) stealthlogin).getHandler();
				log.info(logPrefix + " version " + pdfFile.getVersion() + " Permissions detected...");
			}
			else 
			{
				log.severe(logPrefix + " version " + pdfFile.getVersion() + " not enabled. Permissions not detected");
				this.getServer().getPluginManager().disablePlugin(this);
			}
		}
	}
    
    public boolean isDebugging(final Player player) 
    {
        if (debugees.containsKey(player)) 
        {
            return debugees.get(player);
        } 
        else 
        {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) 
    {
        debugees.put(player, value);
    }
}

