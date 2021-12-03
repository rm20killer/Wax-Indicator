package dev.mrbutters.me.waxindicator;

import dev.mrbutters.me.waxindicator.listener.PlayerInteractEventListener;
import dev.mrbutters.me.waxindicator.listener.PlayerJoinEventListener;
import dev.mrbutters.me.waxindicator.utils.VersionChk;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.UnknownHostException;

public final class WaxIndicator extends JavaPlugin {

    private static WaxIndicator instance;
    public boolean UP2Date = true;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Version Check
        String pluginName = this.getName();
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    VersionChk.checkVersion(pluginName, 93197);
                } catch (UnknownHostException e) {
                    VersionChk.noConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(this);

        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
    }

    public static WaxIndicator getInstance() {
        return instance;
    }

}
