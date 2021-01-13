package me.matyyy.api;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import me.matyyy.bukkit.config.MainConfig;

public interface IMain {

	public ISecondMain getPlugin();
	
	public MainConfig getPluginConfig();
	
	public InputStream getResource(String fileName);
	
	public File getDataFolder();
	
	public void runTask(Runnable runnable, long replace);
	
	public void runTaskLaterAsynchronously(Runnable run, long time);
	
	public void runTaskAsync(Runnable runnable);
	
	public void runTaskDelayed(Runnable runnable);
	
	public Player getPlayer(UUID uuid);
	
	public Scoreboard newScoreboard();
	
	public void sendMessage(String msg);
}
