package me.matyyy.bukkit.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PlayerDistance {

	private Player player;
	private UUID uuid;
	private World world;
	private Location center;
	private double size = 1.000;
	private Location l, p;
	private Location f, g;
	
	public PlayerDistance(Player p) {
		this.player = p;
		this.uuid = p.getUniqueId();
		this.center = p.getLocation();
		this.world = p.getWorld();
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void update(){
		if(this.center == null) return;
		if(this.size < 1) return;
		if(this.world == null) this.world = Bukkit.getWorlds().get(0);
		if(this.world != null){
			double lx = this.center.getBlockX() + this.size;
			double lz = this.center.getBlockZ() + this.size;
			
			double px = this.center.getBlockX() - this.size;
			double pz = this.center.getBlockZ() - this.size;
			
			Vector l = new Vector(lx, 0, lz);
			Vector p = new Vector(px, this.world.getMaxHeight(), pz);
			
			this.l = l.toLocation(this.world);
			this.p = p.toLocation(this.world);
		}
	}
	
	public boolean isIn(Location loc){
		this.update();
		if(loc == null || this.l == null || this.p == null) return false;
		if(!this.center.getWorld().equals(loc.getWorld())) return false;
		if(loc.getBlockX() > this.getLowerX() && loc.getBlockX() < this.getUpperX()) {
            if(loc.getBlockY() > this.getLowerY() && loc.getBlockY() < this.getUpperY()) {
                if(loc.getBlockZ() > this.getLowerZ() && loc.getBlockZ() < this.getUpperZ()){
                    return true;
                }
            }
        }
		return false;
	}
	
	public int getUpperX(){
		int x = this.l.getBlockX();
		int y = this.p.getBlockX();
		if(y < x) return x;
		return y;
	}
	
	public int getUpperY(){
		int x = this.l.getBlockY();
		int y = this.p.getBlockY();
		if(y < x) return x;
		return y;
	}
	
	public int getUpperZ(){
		int x = this.l.getBlockZ();
		int y = this.p.getBlockZ();
		if(y < x) return x;
		return y;
	}
	
	public int getLowerX(){
		int x = this.l.getBlockX();
		int y = this.p.getBlockX();
		if(x > y) return y;
		return x;
	}
	
	public int getLowerY(){
		int x = this.l.getBlockY();
		int y = this.p.getBlockY();
		if(x > y) return y;
		return x;
	}
	
	public int getLowerZ(){
		int x = this.l.getBlockZ();
		int y = this.p.getBlockZ();
		if(x > y) return y;
		return x;
	}

	public int getUPperX(){
		int x = this.f.getBlockX();
		int y = this.g.getBlockX();
		if(y < x) return x;
		return y;
	}
	
	public int getUPperY(){
		int x = this.f.getBlockY();
		int y = this.g.getBlockY();
		if(y < x) return x;
		return y;
	}
	
	public int getUPperZ(){
		int x = this.f.getBlockZ();
		int y = this.g.getBlockZ();
		if(y < x) return x;
		return y;
	}
	
	public int getLOwerX(){
		int x = this.f.getBlockX();
		int y = this.g.getBlockX();
		if(x > y) return y;
		return x;
	}
	
	public int getLOwerY(){
		int x = this.f.getBlockY();
		int y = this.g.getBlockY();
		if(x > y) return y;
		return x;
	}
	
	public int getLOwerZ(){
		int x = this.f.getBlockZ();
		int y = this.g.getBlockZ();
		if(x > y) return y;
		return x;
	}
}
