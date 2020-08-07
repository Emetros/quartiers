package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.logging.Logger;


public class main extends JavaPlugin {
    private static main instance;
    private int task = -1;

    static main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        this.getCommand("createquartier").setExecutor(new commands(this));
        this.getCommand("quartiers").setExecutor(new commands(this));
        instance = this;
        startGenerator();
    }

    public void startGenerator() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                File folder = new File("plugins/Quartiers/Generators");
                File[] dirListing = folder.listFiles();
                if(dirListing != null){
                    for(File generatorList : dirListing) {

                        Logger log = Bukkit.getLogger();
                        log.info("" + generatorList);

                        FileConfiguration config = YamlConfiguration.loadConfiguration(generatorList);

                        String a = config.getString("area");
                        String m = config.getString(a + "-values.material");
                        String x = config.getString(a + "-values.x");
                        String y = config.getString(a + "-values.y");
                        String z = config.getString(a + "-values.z");
                        String w = config.getString(a + "-values.world");
                        String q = config.getString(a + "-values.amount");
                        String t = config.getString(a + "-values.time");

                        new ressourceGeneratorLoader(m, q, x, y, z, t, w);
                    }
                }

            }
        }, 1);
    }

    public int getScheduledTask() {
        return task;
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage("Quartiers unloaded");
    }
}
