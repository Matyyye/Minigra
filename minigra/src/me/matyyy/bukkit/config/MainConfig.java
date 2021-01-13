/*
 * Decompiled with CFR 0_132.
 */
package me.matyyy.bukkit.config;

import java.io.File;
import java.util.Collections;
import java.util.List;

import me.matyyy.api.ISecondMain;

public abstract class MainConfig
extends AbstractConfigFile {
	
    protected final ISecondMain plugin;

    public MainConfig(File file, ISecondMain plugin) {
        super(file);
        this.plugin = plugin;
    }

    public boolean vaultMultipliers() {
        return this.getBoolean("Vault.Use Multipliers", false);
    }

    public boolean isOnline() {
        return this.getBoolean("Online Mode", true);
    }

    public List<String> getCommandAliases() {
        return this.getStringList("General.Command.Aliases", Collections.emptyList());
    }

    public String getOdczytaj() {
        return this.getString("Odczytaj.ten", "Nie wiem, ale sie dowiem! :D");
    }

    public String getServerName() {
        return this.getString("Multipliers.Server", "default");
    }

    public boolean isDebug() {
        return this.getBoolean("General.Logging.Debug.Enabled", false);
    }

    public boolean isDebugFile() {
        return this.getBoolean("General.Logging.Debug.File", true);
    }
}

