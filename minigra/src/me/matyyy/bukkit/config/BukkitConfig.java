package me.matyyy.bukkit.config;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.matyyy.Main;
import me.matyyy.PluginMain;

public class BukkitConfig extends MainConfig {

	private final FileConfiguration config;
	
	public BukkitConfig(File file, PluginMain main) {
		super(file, main);
		this.config = ((Main) main.getMainPlugin()).getConfig();
        this.reload();
    }

    @Override
    public Object get(String path) {
        return this.config.get(path);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return this.config.getConfigurationSection(path);
    }

    @Override
    public final void reload() {
        ((Main) this.plugin.getMainPlugin()).reloadConfig();
    }


	@Override
	public void set(String var0, Object var1) {
		this.set(var0, var1);
	}

	@Override
	public void save() {
		 ((Main) this.plugin.getMainPlugin()).saveConfig();
	}
}
