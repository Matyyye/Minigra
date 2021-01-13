package me.matyyy.bukkit.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

import me.matyyy.base.User;
import me.matyyy.bukkit.SecondMain;

public abstract class Arena {

	protected final SecondMain plugin;
	private final String name;
	protected Location defaultSpawn, goniacySpawn, uciekajacySpawn;
	private World world;
	protected List<User> gracze = new ArrayList<>();
	private int max, min, goniacych;
	public ArenaGroup group;
	protected boolean rozpoczecie;
	
	public Arena(SecondMain secondMain, String name, Location loc, Location loc1, Location loc2, int m, int mn, int g) {
		this.plugin = secondMain;
		this.name = name;
		this.defaultSpawn = loc;
		this.goniacySpawn = loc1;
		this.uciekajacySpawn = loc2;
		this.world = loc.getWorld();
		this.max = m;
		this.min = mn;
		this.goniacych = g;
	}
	
	public ArenaGroup getArenaGroup() {
		if(this.group == null) {
			this.group = new ArenaGroup();
		}
		return this.group;
	}
	
	protected boolean itsPlayers() {
		return this.gracze.size() <= 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public final Location getSpawnDefault() {
		return this.defaultSpawn;
	}
	
	public final Location getSpawnGoniacy() {
		return this.goniacySpawn;
	}
	
	public final Location getSpawnUciekajacy() {
		return this.uciekajacySpawn;
	}
	
	public int getGoniacych() {
		return this.goniacych;
	}
	
	public int getGraczy() {
		return this.gracze.size();
	}
	
	public List<User> getPlayers() {
		return this.gracze;
	}
	
	public boolean canJoin() {
		if(this.gracze.size() >= this.max && this.rozpoczecie == true) {
			return false;
		}
		return true;
	}
	
	public int getMin() {
		return this.min;
	}
	
	public abstract void runTask();
	
	protected boolean isOnline(User user) {
		return user != null && user.getPlayer() != null && user.getPlayer().isOnline();
	}
	
	protected boolean checkMinPlayers() {
		if(this.getGraczy() >= this.min) {
			return true;
		}
		return false;
	}
	
	protected boolean returnMinPlayers() {
		if(this.min > this.getGraczy()) {
			return true;
		}
		return false;
	}
	
	public abstract void returnGame();
	
	protected boolean checkMaxPlayers() {
		if(this.getGraczy() >= this.max) {
			return true;
		}
		return false;
	}
	
	public void addGracz(User user) {
		if(!this.gracze.contains(user)) this.gracze.add(user);
	}
	
	public void removeGracz(User user) {
		if(this.gracze.contains(user)) {
			this.gracze.remove(user);
			user.getPlayer().setHealth(20);
		}
	}
}
