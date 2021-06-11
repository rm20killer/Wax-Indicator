package dev.mrbutters.me.waxindicator;

import dev.mrbutters.me.waxindicator.listener.PlayerInteractEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaxIndicator extends JavaPlugin {

    private static WaxIndicator instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
    }

    public static WaxIndicator getInstance() {
        return instance;
    }

}
