package net.craftrepo.StealthLogin;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;

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
	private String stealthLeaveMessage = null;
	private String joinMessage;
	private String leaveMessage;

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
		if (StealthLogin.Permissions.has(player, "stealthlogin.part") || StealthLogin.Permissions.has(player, "stealthlogin.*") || StealthLogin.Permissions.has(player, "*"))
		{
			event.setLeaveMessage(stealthLeaveMessage);
			
		}
		else
		{
			leaveMessage = event.getLeaveMessage();
			event.setLeaveMessage(leaveMessage);
		}
	}
}

