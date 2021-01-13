package me.matyyy.bukkit.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.matyyy.api.ISecondMain;

public class FileManager {

	private final ISecondMain plugin;
	private final File file;
	
	public FileManager(ISecondMain zp) {
		this.plugin = zp;
		
		this.file = new File(this.plugin.getMainPlugin().getDataFolder(), "config.yml");
	}
	
	public void createFiles() {
		if(!this.plugin.getMainPlugin().getDataFolder().exists()) {
			this.plugin.getMainPlugin().getDataFolder().mkdirs();
		}
		if(!this.file.exists()) {
			this.copy(this.plugin.getMainPlugin().getResource("config.yml"), this.file);
		}
	}
	
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] bf = new byte[1024];
			int l;
			while((l = in.read(bf)) > 0) {
				out.write(bf, 0, l);
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
