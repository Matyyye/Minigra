package me.matyyy.bukkit.utils;

import org.bukkit.Location;

public class LocBlockArena {

	public final Location location;
	public final int i;
	public final boolean kan;
	
	public LocBlockArena(Location l, int i, boolean k) {
		this.location = l;
		this.i = i*2;
		this.kan = k;
	}
}
