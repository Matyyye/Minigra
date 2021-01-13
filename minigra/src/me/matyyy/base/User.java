package me.matyyy.base;

import java.util.UUID;

import org.bukkit.entity.Player;

import me.matyyy.GraAPI;

public class User {

	private final UUID uuid;
	public int wygrane, dedy, zabojstwa, rozareny;
	
	public User(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Player getPlayer() {
		if(this.uuid == null) {
			return null;
		}
		return GraAPI.getPlugin().getMainPlugin().getPlayer(this.uuid);
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
}
