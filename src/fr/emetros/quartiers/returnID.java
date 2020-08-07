package fr.emetros.quartiers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;

public class returnID {
    public returnID() {
        String areaName;
        ArrayList<String> listID = new ArrayList<String>();
        File folder = new File("plugins/Quartiers/Generators");
        File[] dirList = folder.listFiles();
        for(File getID: dirList) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(getID);
            areaName = config.getString("area");
            listID.add(areaName);
        }
    }
}
