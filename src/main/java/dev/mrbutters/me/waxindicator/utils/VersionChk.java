package dev.mrbutters.me.waxindicator.utils;

import dev.mrbutters.me.waxindicator.WaxIndicator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class VersionChk {


    private static final Logger log = Logger.getLogger("Minecraft");

    public static void checkVersion(String name, int id) throws Exception {

        String url = "https://api.spigotmc.org/legacy/update.php?resource="+id;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        WaxIndicator.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.RESET +"Checking for new versions...");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String spigotVerison = response.toString();
        String ver = Bukkit.getServer().getPluginManager().getPlugin(name).getDescription().getVersion();
        if (spigotVerison.equals(ver)) {
            WaxIndicator.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.DARK_GREEN + "Plugin is up-to-date.");
        } else {
            WaxIndicator.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.RED + "UPDATE FOUND: " + ChatColor.GREEN + "https://www.spigotmc.org/resources/"+id+"/");
            WaxIndicator.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.GOLD + "Version: " + ChatColor.GREEN + response.toString() + ChatColor.AQUA + " Using Version: " + ChatColor.DARK_AQUA + ver);
            WaxIndicator.getInstance().UP2Date = false;
        }

        // Config Version:
        double configVersion = WaxIndicator.getInstance().getConfig().getDouble("ConfigVersion");



    }

    public static void noConnection(){
        WaxIndicator.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[Wax Indicator] " + ChatColor.LIGHT_PURPLE + "Cannot check for update's - No internet connection!");
    }

}
