package me.matyyy;

import me.matyyy.api.ISecondMain;

public class GraAPI {

	private static ISecondMain PLUGIN = null;
	
	public static void setPlugin(ISecondMain api) {
		if(PLUGIN == null) {
			PLUGIN = api;
		}
	}
	
	public static ISecondMain getPlugin() {
		return PLUGIN;
	}
	
	private GraAPI() {
	}
}
