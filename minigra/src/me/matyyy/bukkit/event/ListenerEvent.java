package me.matyyy.bukkit.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.matyyy.api.ISecondMain;
import me.matyyy.base.User;
import me.matyyy.bukkit.scoreboard.ScorePlayer;
import me.matyyy.bukkit.utils.Arena;
import me.matyyy.bukkit.utils.LocBlockArena;

public class ListenerEvent implements Listener {

	private final ISecondMain plugin;

	public ListenerEvent(ISecondMain iSecondMain) {
		this.plugin = iSecondMain;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
			
		Player player = event.getPlayer();
			
		if(player.getWorld().getName().equals("World")) {
			return;
		}
		
	    Location loc = event.getTo();
	    Location from = event.getFrom();
	    
	    this.plugin.getMainPlugin().runTaskAsync(() -> {
			if(from == null) return;
			if(from.getBlockX() == loc.getBlockX() && from.getBlockZ() == loc.getBlockZ()) return;
			
		    List<LocBlockArena> list = new ArrayList<>();
		    
		    for(int i = -1; 2 > i; i++) {
		    	list.add(new LocBlockArena(new Location(loc.getWorld(), (loc.getX() + i), loc.getY() + player.getEyeHeight(), loc.getZ()), i, true));
		    	list.add(new LocBlockArena(new Location(loc.getWorld(), loc.getX(), loc.getY() + player.getEyeHeight(), (loc.getZ() + i)), i, false));
		    }
		    
		    for(LocBlockArena l : list) {
		    	if(l.location.getBlock() != null) {
		    		if(l.location.getBlock().getType() == Material.COBBLESTONE) {
		    			l.location.setYaw(loc.getYaw());
		    			l.location.setPitch(loc.getPitch());
		    			if(l.kan == true) {
		    				player.teleport(l.location.add(l.i, 0, 0));
		    			} else {
		    				player.teleport(l.location.add(0, 0, l.i));
		    			}
		    		}
		    	}
		    }
	    });
	}
	
	@EventHandler
	public void onSignUse(PlayerInteractEvent e) {
        if (e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getState() instanceof Sign) {
            Player player = e.getPlayer();
            Sign  sign = (Sign) e.getClickedBlock().getState();
            String name = sign.getLine(0);
            
            Arena arena = this.plugin.get(name);
            User user = this.plugin.getUser(player.getUniqueId());
            if(arena != null) {

            	if(arena.canJoin()) {
            		Location location = arena.getSpawnDefault();
            		if(location == null) {
            			player.sendMessage(this.plugin.translateColor("&c&oArena&8: &fPoinformuj Administratora o tym b³êdzie! (&oKOD B£ÊDU #402&f)"));
            			return;
            		}
            		
            		arena.addGracz(user);
            		user.rozareny += 1;
            		player.teleport(location);
            		player.sendMessage(this.plugin.translateColor("&c&oArena&8: &eAktualnie liczba graczy na arenie&8: &3&n" + arena.getGraczy()));

            	} else {
            		player.sendMessage(this.plugin.translateColor("&c&oArena&8: &fOsi¹gniêto &c&nmaksymaln¹&f liczbê graczy na tej arenie!"));
            	}
            }
            
        }
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		User user = this.plugin.getUser(player.getUniqueId());
		
		World world = player.getWorld();
		
		Arena arena = this.plugin.get(world);
		
		if(arena != null) {
			arena.removeGracz(user);
		}
		
		ScorePlayer scorePlayer = this.plugin.getScorePlayer(player);
		scorePlayer.remove();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ScorePlayer scorePlayer = this.plugin.getScorePlayer(player);
		scorePlayer.setScoreboard();
	}
	
	@EventHandler
	public void playerChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		User user = this.plugin.getUser(player.getUniqueId());
		World world = e.getFrom();
		Arena arena = this.plugin.get(world);
		
		if(arena != null) {
			arena.removeGracz(user);
		}
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			return;
		}

		Player player = (Player) e.getEntity();
		World world = player.getWorld();
		
		Arena arena = this.plugin.get(world);
		
		if(arena != null) {
			if(arena.getArenaGroup().isGoniacy(player)) {
				e.setCancelled(true);
				e.getDamager().sendMessage(this.plugin.translateColor("&cNie masz uprawnieñ do bicia &3&ngoni¹cego&c&4!"));
			}
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e) {
		Player player = (Player) e.getEntity();
		User user = this.plugin.getUser(player.getUniqueId());
		World world = player.getWorld();
		
		Arena arena = this.plugin.get(world);
		
		if(arena != null) {
			arena.removeGracz(user);
		}
	}
}
