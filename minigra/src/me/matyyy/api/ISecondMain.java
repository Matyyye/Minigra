package me.matyyy.api;

import java.util.List;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.matyyy.base.User;
import me.matyyy.bukkit.config.MainConfig;
import me.matyyy.bukkit.scoreboard.ScorePlayer;
import me.matyyy.bukkit.utils.Arena;

public interface ISecondMain {

	public IMain getMainPlugin();
	
	public MainConfig getConfig();
	
	public Arena get(String name);
	
	public Arena get(World world);
	
	public void enable();
	
	public void disable();
	
	String translateColor(String string);
	
	List<String> translateColorList(List<String> string);
	
	public User getUser(UUID uuid);
	
	public ScorePlayer getScorePlayer(Player player);
}
