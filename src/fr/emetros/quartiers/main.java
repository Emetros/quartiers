package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {
    private static main instance;
    private int task = 0;

    static main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        this.getCommand("quartierscreate").setExecutor(new commands(this));
        this.getCommand("quartiersreload").setExecutor(new commands(this));
        this.getCommand("quartierslist").setExecutor(new commands(this));
        this.getCommand("quartiersenable").setExecutor(new commands(this));
        this.getCommand("quartiersdisable").setExecutor(new commands(this));
        instance = this;
        new quartiersLoader();
    }


    @Override
    public void onDisable() {
        this.saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage("Quartiers unloaded");
    }
}
