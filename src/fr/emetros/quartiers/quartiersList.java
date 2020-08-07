package fr.emetros.quartiers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class quartiersList {
    public quartiersList(Player player) {
        String areaName;
        File folder = new File("plugins/Quartiers/Generators");
        File[] dirList = folder.listFiles();
        for(File getID: dirList) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(getID);
            areaName = config.getString("area");
            player.sendMessage(ChatColor.GREEN + areaName);
        }
    }
}
