package dev.mrbutters.me.waxindicator.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexFormat {

    private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    public static String format(String msg) {
        String version = Bukkit.getVersion();
        if(version.startsWith("1."))
        {
            int majorVersion = Integer.parseInt(version.substring(2, 4));
            if(majorVersion >= 17)
            {
                msg = pattern.matcher(msg).replaceAll(matchResult ->
                        ChatColor.of(matchResult.group().replace("&#", "#")) + ""
                );
            }
        }

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
