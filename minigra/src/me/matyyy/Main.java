package me.matyyy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

import me.matyyy.api.IMain;
import me.matyyy.bukkit.config.BukkitConfig;
import me.matyyy.bukkit.config.MainConfig;

public class Main extends JavaPlugin implements IMain {

	private PluginMain pluginMain;
	private BukkitConfig config;
	private List<BukkitTask> listSchedulers = new ArrayList<>();
	
	@Override
	public void onLoad() {
		this.pluginMain = new PluginMain(this);
		this.config = new BukkitConfig(null, this.pluginMain);
	}
	
	@Override
	public void onEnable() {
		this.pluginMain.enable();
	}
	
	@Override
	public void onDisable() {
		this.pluginMain.disable();
		this.listSchedulers.stream().filter(this::hasNext).collect(Collectors.toList()).forEach(this::cancel);
	}
	
	@Override
	public PluginMain getPlugin() {
		return this.pluginMain;
	}
	
	@Override
	public MainConfig getPluginConfig() {
		return this.config;
	}
	
	@Override
	public void sendMessage(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	@Override
	public void runTask(Runnable runnable, long r) {
		this.listSchedulers.add(Bukkit.getScheduler().runTaskTimerAsynchronously(this, runnable, r, 20L));
	}
	
	@Override
	public Player getPlayer(UUID uuid) {
		Player player;
		if((player = Bukkit.getPlayer(uuid)) != null) {
			return player;
		}
		return null;
	}
	
	@Override
	public void runTaskLaterAsynchronously(Runnable run, long time) {
		Bukkit.getScheduler().runTaskLaterAsynchronously(this, run, time);
	}
	
	@Override
	public void runTaskAsync(Runnable runnable) {
		Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
	}
	
	@Override
	public void runTaskDelayed(Runnable runnable) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, runnable);
	}
	
	@Override
	public Scoreboard newScoreboard() {
		return Bukkit.getScoreboardManager().getNewScoreboard();
	}
	
	private boolean hasNext(BukkitTask task) {
		return task != null;
	}
	
	private void cancel(BukkitTask task) {
		this.listSchedulers.remove(task);
		task.cancel();
	}
}
