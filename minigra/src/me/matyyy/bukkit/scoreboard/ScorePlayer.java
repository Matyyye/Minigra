package me.matyyy.bukkit.scoreboard;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.matyyy.api.ISecondMain;
import me.matyyy.base.User;

public class ScorePlayer {

	private final ISecondMain api;
	private Player p;
	private UUID uuid;
	private Scoreboard scoreboard;
	
	public ScorePlayer(ISecondMain zomon, Player player) {
		this.api = zomon;
			this.api.getMainPlugin().sendMessage("score 1");
		this.p = player;
		this.uuid = player.getUniqueId();
		this.scoreboard = this.api.getMainPlugin().newScoreboard();
		
			this.api.getMainPlugin().sendMessage("score 2");
		
		User user = this.api.getUser(player.getUniqueId());
		
			this.api.getMainPlugin().sendMessage("score 3");
		
		Objective obj = this.scoreboard.registerNewObjective("Score", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(this.api.translateColor("&C&lIP SERWERA"));
		
			this.api.getMainPlugin().sendMessage("score 4");
		
		Team team11 = this.scoreboard.registerNewTeam("t11");
		team11.addEntry(this.api.translateColor("&0 "));
		obj.getScore(this.api.translateColor("&0 ")).setScore(11);
		
		Team team10 = this.scoreboard.registerNewTeam("t10");
		team10.addEntry(this.api.translateColor("&7- &6Nick&8: "));
		obj.getScore(this.api.translateColor("&7- &6Nick&8: ")).setScore(10);
		team10.setSuffix(this.api.translateColor("&7&n" + player.getName()));
		
		Team team8 = this.scoreboard.registerNewTeam("t8");
		team8.addEntry(this.api.translateColor("&1 "));
		obj.getScore(this.api.translateColor("&1 ")).setScore(9);
		
		Team team9 = this.scoreboard.registerNewTeam("t9");
		team9.addEntry(this.api.translateColor("&7- &6Zgony&8: "));
		obj.getScore(this.api.translateColor("&7- &6Zgony&8: ")).setScore(8);
		
		this.api.getMainPlugin().runTask(() -> {
			team9.setSuffix(this.api.translateColor("&2" + user.rozareny));
		}, 10L);
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public void setScoreboard() {
		this.p.setScoreboard(this.scoreboard);
	}
	
	public void remove() {
		this.scoreboard = null;
		this.uuid = null;
		this.p = null;
	}
}
