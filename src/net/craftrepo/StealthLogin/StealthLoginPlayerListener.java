package net.craftrepo.StealthLogin;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * CraftRepo StealthLogin for Bukkit
 * @author AllGamer
 * 
 * Copyright 2011 AllGamer, LLC.
 * See LICENSE for licensing information.
 */

public class StealthLoginPlayerListener extends PlayerListener 
{
    @SuppressWarnings("unused")
	private final StealthLogin plugin;
	private String stealthMessage = null;
	private String joinMessage;
	private String kickMessage;
	private String quitMessage;

    public StealthLoginPlayerListener(StealthLogin instance) 
    {
        plugin = instance;
    }

	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (StealthLogin.Permissions.has(player, "stealthlogin.join"))
		{
			StealthLogin.loggedout.put(player, true);
			event.setJoinMessage(stealthMessage);
			//Begin MCMA COMPAT
			System.out.println(player.getName() + " logged in with entity id" + player.getEntityId());
			//End MCMA COMPAT
			StealthLogin.log.info(StealthLogin.logPrefix + " " + player.getName() + " logged in secretly!");
		}
		else
		{
			joinMessage = event.getJoinMessage();
			event.setJoinMessage(joinMessage);
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		if (StealthLogin.Permissions.has(player, "stealthlogin.quit"))
		{
			if (StealthLogin.loggedout.get(player))
			{
				StealthLogin.loggedout.remove(player);
			}
			event.setQuitMessage(stealthMessage);
			StealthLogin.log.info(StealthLogin.logPrefix + " " + player.getName() + " logged out secretly!");
		}
		else
		{
			quitMessage = event.getQuitMessage();
			event.setQuitMessage(quitMessage);
		}
	}
	
	public void onPlayerKick(PlayerKickEvent event)
	{
		Player player = event.getPlayer();
		if (StealthLogin.Permissions.has(player, "stealthlogin.kick"))
		{
			event.setLeaveMessage(stealthMessage);
			StealthLogin.log.info(StealthLogin.logPrefix + " " + player.getName() + " was kicked quietly!");
		}
		else
		{
			kickMessage = event.getLeaveMessage();
			event.setLeaveMessage(kickMessage);
		}
	}
}

