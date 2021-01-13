package me.matyyy.bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.matyyy.GraAPI;
import me.matyyy.api.IMain;
import me.matyyy.api.ISecondMain;
import me.matyyy.base.User;
import me.matyyy.bukkit.config.FileManager;
import me.matyyy.bukkit.config.MainConfig;
import me.matyyy.bukkit.scoreboard.ScorePlayer;
import me.matyyy.bukkit.utils.Arena;
import me.matyyy.bukkit.utils.ArenaStart;

public class SecondMain implements ISecondMain {

	protected final IMain plugin;
	private final FileManager fileManager;
	private final Random random = new Random();
	private List<Arena> arens = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private List<ScorePlayer> scorePlayers = new ArrayList<>();
	
	public SecondMain(IMain pluginAPI) {
		GraAPI.setPlugin(this);
		this.plugin = pluginAPI;
		this.fileManager = new FileManager( this );
		this.fileManager.createFiles();
	}
	
	@Override
	public IMain getMainPlugin() {
		return this.plugin;
	}

	@Override
	public MainConfig getConfig() {
		return this.plugin.getPluginConfig();
	}
	
	@Override
	public void enable() {
		for(String string : this.getConfig().getConfigurationSection("Areny").getKeys(false)) {
			Arena arena = new ArenaStart( this,
					this.translateColor(this.getConfig().getString("Areny." + string + ".NazwaAreny")),
					this.parseLocation(this.getConfig().getString("Areny." + string + ".Default-Spawn")),
					this.parseLocation(this.getConfig().getString("Areny." + string + ".Goniacy-Spawn")),
					this.parseLocation(this.getConfig().getString("Areny." + string + ".Uciekajacy-Spawn")),
					this.getConfig().getInt("Areny." + string + ".Max-PlayersInGame", 8),
					this.getConfig().getInt("Areny." + string + ".Min-PlayersInGame", 4),
					this.getConfig().getInt("Areny." + string + ".Goniacych", 1),
					this.getConfig().getDouble("Areny." + string + ".Goni¹cySetMaxHealth", 30.0));
			this.arens.add(arena);
		}
	}

	@Override
	public void disable() {
		this.arens.clear();
	}

	@Override
	public Arena get(String name) {
		for(Arena arena : this.arens) {
			if(arena != null && arena.getName() != null && arena.getName().equalsIgnoreCase(name)) {
				return arena;
			}
		}
		return null;
	}

	@Override
	public Arena get(World world) {
		for(Arena arena : this.arens) {
			if(arena.getWorld() != null && arena.getWorld().equals(world)) {
				return arena;
			}
		}
		return null;
	}
	
	@Override
	public String translateColor(String string) {
		if(string == null) {
			return "";
		}
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	@Override
	public List<String> translateColorList(List<String> string) {
		return string.stream().map(this::translateColor).collect(Collectors.toList());
	}
	
	public List<Arena> getArens() {
		return this.arens;
	}
	
	public Location parseLocation(String string){
		if(string == null) return null;
		String[] shs = string.split(",");
		if(shs.length < 4) return null;
		World world = Bukkit.getWorld(shs[0]);
		if(world == null) world = Bukkit.getWorlds().get(0);
		Location loc = new Location(world, Integer.valueOf(shs[1]), Integer.valueOf(shs[2]), Integer.valueOf(shs[3]));
		return loc;
	}
	
	public int getRandInt(int min, int max) {
		Validate.isTrue(max > min, "Maksymalna liczba nie moze byc wieksza od minimalnej!");
		return this.random.nextInt(max - min + 1) + min;
	}

	@Override
	public User getUser(UUID uuid) {
		for(User user : this.users) {
			if(user.getUUID().equals(uuid)) {
				return user;
			}
		}
		User user = new User(uuid);
		this.users.add(user);
		return user;
	}

	@Override
	public ScorePlayer getScorePlayer(Player player) {
		this.getMainPlugin().sendMessage("scoreplayer 1");
		for(ScorePlayer score : this.scorePlayers) {
			if(score != null && score.getUUID() != null && score.getUUID().equals(player.getUniqueId())) {
				return score;
			}
		}
		this.getMainPlugin().sendMessage("scoreplayer 2");
		ScorePlayer score = new ScorePlayer(this, player);
		this.getMainPlugin().sendMessage("scoreplayer 3");
		this.scorePlayers.add(score);
		this.getMainPlugin().sendMessage("scoreplayer 4");
		return score;
	}
}
