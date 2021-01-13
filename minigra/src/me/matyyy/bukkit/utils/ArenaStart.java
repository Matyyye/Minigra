package me.matyyy.bukkit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;

import me.matyyy.base.User;
import me.matyyy.bukkit.SecondMain;

public class ArenaStart extends Arena {
	
	double health;

	public ArenaStart(SecondMain secondMain, String name, Location loc, Location loc1, Location loc2, int m, int mn, int g, double h) {
		super(secondMain, name, loc, loc1, loc2, m, mn, g);
		this.health = h;
	}

	private boolean start, nowStart;
	private int czas;
	
	@Override
	public void runTask() {
		this.plugin.getMainPlugin().runTask(() -> {
			
			if(this.rozpoczecie == true || this.itsPlayers()) {
				this.plugin.getMainPlugin().sendMessage("runTask -> return");
				return;
			}
			
			if(this.start == false && this.checkMinPlayers()) {
				this.setStart(true);
				this.czas = 30;
				this.plugin.getMainPlugin().sendMessage("runTask -> Start Game");
			}
			
			if(this.getStart() && this.returnMinPlayers()) {
				this.czas = 30;
				this.plugin.getMainPlugin().sendMessage("runTask -> return new Game");
			}
			
			if(this.checkMaxPlayers()) {
				this.setStart(true);
				this.nowStart = true;
				this.plugin.getMainPlugin().sendMessage("runTask -> set Max Players");
			}
			
			this.plugin.getMainPlugin().sendMessage("runTask -> checkCzas -1");
			if(this.getStart()) {
				this.plugin.getMainPlugin().sendMessage("runTask -> checkCzas 0");
				
				List<User> players;
				(players = this.gracze.stream().filter(this::isOnline).collect(Collectors.toList())).forEach(player -> {
					player.getPlayer().sendMessage(this.plugin.translateColor("&3Mecz rozpoczenie siê za &4&n" + this.getCzas() + "s&8."));
				});
				
				if(this.checkCzas()) {
					
					this.plugin.getMainPlugin().sendMessage("runTask -> checkCzas 1");
					
					List<Integer> randomList = new ArrayList<>();
					/*for(int i = 0; i < this.getGoniacych(); i++) {
						
						this.plugin.getMainPlugin().sendMessage("Losowanie...");
						int random = this.plugin.getRandInt(0, (players.size() - 1));
						
						randomList.add(random);
						this.plugin.getMainPlugin().sendMessage("Random liczba: " + random);
					}*/
					
					for(int i = 0; 100 > i; i++) {
						
						this.plugin.getMainPlugin().sendMessage("Losowanie...");
						int random = this.plugin.getRandInt(0, (players.size() - 1));
						
						if(!randomList.contains(random)) {
							this.plugin.getMainPlugin().sendMessage("Random liczba: " + random + " za prób¹: " + i);
							randomList.add(random);	
						}
						
						if(randomList.size() >= this.getGoniacych()) {
							this.plugin.getMainPlugin().sendMessage("Random liczb: " + randomList.size() + "/" + this.getGoniacych());
							break;
						}
					}
					
					for(int i = 0; i < players.size(); i++) {
						
						//this.plugin.getMainPlugin().sendMessage("Ilosc graczy: " + players.size() + " / Liczba i:" + i);
						User user;
						
						if((user = players.get(i)) == null || user.getPlayer() == null || !user.getPlayer().isOnline()) {
							this.plugin.getMainPlugin().sendMessage("Gracz jest --> NULL");
							this.plugin.getMainPlugin().sendMessage("Ilosc graczy: " + players.size());
							this.plugin.getMainPlugin().sendMessage("Liczba i:" + i);
							continue;
						}
						
						//this.plugin.getMainPlugin().sendMessage("runTask -> checkCzas 2");
						
						if(randomList.contains(i)) {
							this.plugin.getMainPlugin().sendMessage(user.getPlayer().getName() + " Goniacy = " + i);
							this.getArenaGroup().gracze.put(user.getPlayer(), EnumPvP.Goni¹cy);
							user.getPlayer().setMaxHealth(this.health);
							user.getPlayer().setHealth(this.health);
							user.getPlayer().sendMessage(this.plugin.translateColor("&aTwoja rola na arenie&8: &3&nGoni¹cy"));
							user.getPlayer().teleport(this.goniacySpawn);
						} else {
							this.plugin.getMainPlugin().sendMessage(user.getPlayer().getName() + " Uciekaj¹cy = " + i);
							this.getArenaGroup().gracze.put(user.getPlayer(), EnumPvP.Uciekaj¹cy);
							user.getPlayer().sendMessage(this.plugin.translateColor("&aTwoja rola na arenie&8: &6&nUciekaj¹cy"));
							user.getPlayer().teleport(this.uciekajacySpawn);
						}
					}
					
					this.rozpoczecie = true;
					
					if(this.gracze.size() == players.size()) {
						this.plugin.getMainPlugin().sendMessage("Wszystko jest w porz¹dku na tej arenie... Arena wystartowa³a!");
						return;
					}
					
					this.plugin.getMainPlugin().sendMessage("b³¹d: Gracze.size(): " + this.gracze.size() + " / Players.size(): " + players.size());
					
				}
			}
			
		}, 100L);
	}
	
	public void setStart(boolean b) {
		this.start = b;
	}
	
	public boolean getStart() {
		return this.start == true;
	}
	
	public void setNowStart(boolean b) {
		this.nowStart = b;
	}
	
	public boolean getNowStart() {
		return this.nowStart;
	}

	public int getCzas() {
		return this.czas;
	}
	
	public boolean checkCzas() {
		if(this.czas <= 0 || this.nowStart == true) {
			return true;
		}
		this.czas -= 1;
		return false;
	}
	
	@Override
	public void returnGame() {
		this.rozpoczecie = false;
		this.nowStart = false;
		this.start = false;
		this.czas = 30;
		this.group = new ArenaGroup();
	}
	
}
