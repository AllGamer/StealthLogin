package net.craftrepo.StealthLogin;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
//import org.bukkit.event.player.PlayerQuitEvent;

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
	private String stealthJoinMessage = null;
	private String stealthKickMessage = null;
	private String joinMessage;
	private String kickMessage;

    public StealthLoginPlayerListener(StealthLogin instance) 
    {
        plugin = instance;
    }

	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (StealthLogin.Permissions.has(player, "stealthlogin.join") || StealthLogin.Permissions.has(player, "stealthlogin.*") || StealthLogin.Permissions.has(player, "*"))
		{
			event.setJoinMessage(stealthJoinMessage);
			StealthLogin.log.info(StealthLogin.logPrefix + " " + player + " logged in secretly!");
			player.sendMessage(StealthLogin.logPrefix + " You have been logged in secretly!");
		}
		else
		{
			joinMessage = event.getJoinMessage();
			event.setJoinMessage(joinMessage);
		}
	}
	
	public void onPlayerKick(PlayerKickEvent event)
	{
		Player player = event.getPlayer();
		if (StealthLogin.Permissions.has(player, "stealthlogin.kick") || StealthLogin.Permissions.has(player, "stealthlogin.*") || StealthLogin.Permissions.has(player, "*"))
		{
			event.setLeaveMessage(stealthKickMessage);
			StealthLogin.log.info(StealthLogin.logPrefix + " " + player + " was kicked quietly!");
		}
		else
		{
			kickMessage = event.getLeaveMessage();
			event.setLeaveMessage(kickMessage);
		}
	}
}

