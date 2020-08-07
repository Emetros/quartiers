package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class quartiersLoader {

    public quartiersLoader() {

        ArrayList<Integer> listID = new ArrayList<Integer>();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                File folder = new File("plugins/Quartiers/Generators");
                File[] dirListing = folder.listFiles();
                if (dirListing != null) {
                    int taskID = 0;
                    for (File generatorList : dirListing) {

                        Logger log = Bukkit.getLogger();
                        log.info("" + generatorList);

                        FileConfiguration config = YamlConfiguration.loadConfiguration(generatorList);

                        String a = config.getString("area");
                        String stringT = config.getString(a + "-values.material");
                        String integerX = config.getString(a + "-values.x");
                        String integerY = config.getString(a + "-values.y");
                        String integerZ = config.getString(a + "-values.z");
                        String world = config.getString(a + "-values.world");
                        String amount = config.getString(a + "-values.amount");
                        String time = config.getString(a + "-values.time");

                        taskID = taskID++;

                        listID.add(taskID);


                        int x = Integer.parseInt(integerX);
                        int y = Integer.parseInt(integerY);
                        int z = Integer.parseInt(integerZ);
                        int q = Integer.parseInt(amount);
                        int timeInt = Integer.parseInt(time);
                        stringT = stringT.toUpperCase();

                        Material t = Material.getMaterial(stringT);

                        if (t != null) {
                            ItemStack i = new ItemStack(t, q);
                            World w = Bukkit.getWorld(world);
                            Block b = w.getBlockAt(x, y, z);
                            Chest c = (Chest) b.getState();
                            if (b.getType() == Material.CHEST) {
                                listID.set(taskID, scheduler.scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        c.getBlockInventory().addItem(i);
                                    }
                                }, 1, timeInt));
                            } else {
                                main.getInstance().getLogger();
                            }
                        }
                    }
                }

            }
        }, 1);
    }
}
