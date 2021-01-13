package me.matyyy.bukkit.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class ArenaGroup {

	public Map<Player, EnumPvP> gracze = new HashMap<>();
	
	public void clear() {
		this.gracze.clear();
	}
	
	public boolean isGoniacy(Player player) {
		if(this.gracze.containsKey(player) && this.gracze.get(player) == EnumPvP.Goni¹cy) {
			return true;
		}
		return false;
	}
}
