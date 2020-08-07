package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Logger;

public class quartiersList {

    private String a;
    public void quartiersList() {
        File folder = new File("plugins/Quartiers/Generators");
        File[] dirListing = folder.listFiles();
        if (dirListing != null) {
            for (File generatorList : dirListing) {

                Logger log = Bukkit.getLogger();
                log.info("" + generatorList);

                FileConfiguration config = YamlConfiguration.loadConfiguration(generatorList);

                String a = config.getString("area");
            }
        }
    }
}

