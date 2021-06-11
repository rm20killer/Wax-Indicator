package dev.mrbutters.me.waxindicator.listener;

import dev.mrbutters.me.waxindicator.WaxIndicator;
import dev.mrbutters.me.waxindicator.utils.HexFormat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractEventListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        if (e.getAction()!=Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = e.getClickedBlock();
        Player player = e.getPlayer();

        if (block.getType().toString().startsWith("WAXED_")) {
            String message = HexFormat.format(WaxIndicator.getInstance().getConfig().getString("waxedActionBar"));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            if (WaxIndicator.getInstance().getConfig().getBoolean("doSound")) {
                player.playSound(block.getLocation(),
                    Sound.valueOf(WaxIndicator.getInstance().getConfig().getString("soundSettings.sound")),
                    (float) WaxIndicator.getInstance().getConfig().getDouble("soundSettings.vol"),
                    (float) WaxIndicator.getInstance().getConfig().getDouble("soundSettings.pitch"));
            }
            if (WaxIndicator.getInstance().getConfig().getBoolean("doParticle")) {
                block.getWorld().spawnParticle(Particle.WAX_ON, block.getLocation().add(.5,.5,.5), 25, 0.3, 0.3, 0.3);
            }
        }
    }

}
