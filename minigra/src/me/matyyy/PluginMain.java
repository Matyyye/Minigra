package me.matyyy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.matyyy.api.ISecondMain;
import me.matyyy.bukkit.SecondMain;
import me.matyyy.bukkit.event.ListenerEvent;
import me.matyyy.bukkit.utils.Arena;

public class PluginMain extends SecondMain {
	
	public PluginMain(Main pluginAPI) {
		super(pluginAPI);
	}

	@Override
	public void enable() {
		super.enable();
		
		Bukkit.getPluginManager().registerEvents(new ListenerEvent((ISecondMain) this), (Plugin) this.getMainPlugin());
		
		for(Arena arena : this.getArens()) {
			arena.runTask();
		}
	}
}
