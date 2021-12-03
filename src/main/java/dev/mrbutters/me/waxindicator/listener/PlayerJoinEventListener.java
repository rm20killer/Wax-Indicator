package dev.mrbutters.me.waxindicator.listener;

import dev.mrbutters.me.waxindicator.WaxIndicator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        // Update notifications for admins
        if (player.hasPermission("waxindicator.notifyupdate") && !WaxIndicator.getInstance().UP2Date){
            String msgUpdate = ChatColor.translateAlternateColorCodes('&', "&6➤ &eClick &6&lHERE&e to view the latest version.");
            String ver = Bukkit.getServer().getPluginManager().getPlugin("WaxIndicator").getDescription().getVersion();
            player.sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.YELLOW + "Version: " + ChatColor.RED + ver + ChatColor.YELLOW + " is not up to date. Please check your console on next startup or reload.");

            TextComponent message = new TextComponent(msgUpdate);
            message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/wax-indicator.93197/" ) );
            message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click to open on spigot!" ).create() ) );
            player.spigot().sendMessage( message );
        }
    }

}
