package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class quartiersCreate {

    public quartiersCreate(String a, String stringT, String amount, String integerX, String integerY, String integerZ, String time, String world, Player player){
        int x = Integer.parseInt(integerX);
        int y = Integer.parseInt(integerY);
        int z = Integer.parseInt(integerZ);
        int q = Integer.parseInt(amount);

        stringT = stringT.toUpperCase();
        Material t = Material.getMaterial(stringT);
        if (t != null){
            ItemStack i = new ItemStack(t, q);
            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b.getType() == Material.CHEST) {
                File folder = new File("plugins/Quartiers/Generators");
                File[] dirListing = folder.listFiles();
                int xCheckInt = 0;
                int yCheckInt = 0;
                int zCheckInt = 0;
                int checkerResult = 0;
                if (dirListing != null) {
                    for (File generatorList : dirListing) {

                        FileConfiguration configCheck = YamlConfiguration.loadConfiguration(generatorList);

                        String fill = configCheck.getString("area");
                        String xCheck = configCheck.getString(fill + "-values.x");
                        String yCheck = configCheck.getString(fill + "-values.y");
                        String zCheck = configCheck.getString(fill + "-values.z");
                        xCheckInt = Integer.parseInt(xCheck);
                        yCheckInt = Integer.parseInt(yCheck);
                        zCheckInt = Integer.parseInt(zCheck);

                        if (Math.abs(xCheckInt - x) <= 1 && Math.abs(yCheckInt - y) <= 1 && Math.abs(zCheckInt - z) <= 1) {
                            checkerResult = 1;
                        }
                    }
                }

                File f = new File("plugins/Quartiers/Generators", a + ".yml");

                if (f.exists() || checkerResult == 1) {
                    player.sendMessage(ChatColor.RED + "This resources generator already exists, or there is already a resources generator 1 block nearby");
                } else {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(f);

                    config.set("area", a);
                    config.set(a + "-values.material", stringT);
                    config.set(a + "-values.x", integerX);
                    config.set(a + "-values.y", integerY);
                    config.set(a + "-values.z", integerZ);
                    config.set(a + "-values.world", world);
                    config.set(a + "-values.amount", amount);
                    config.set(a + "-values.time", time);
                    config.set(a + "-values.id", 1);

                    try {
                        config.save(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().cancelTasks(main.getInstance());
                    new quartiersLoader();
                    player.sendMessage(ChatColor.GREEN + "A new resources generator has been added");
                }
            } else {
                main.getInstance().getLogger();
            }
        }
    }

    public quartiersCreate(String a, String stringT, String amount, String integerX, String integerY, String integerZ, String world, Player player){
        int x = Integer.parseInt(integerX);
        int y = Integer.parseInt(integerY);
        int z = Integer.parseInt(integerZ);
        int q = Integer.parseInt(amount);

        stringT = stringT.toUpperCase();
        Material t = Material.getMaterial(stringT);
        if (t != null){
            ItemStack i = new ItemStack(t, q);
            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b.getType() == Material.CHEST) {
                File folder = new File("plugins/Quartiers/Generators");
                File[] dirListing = folder.listFiles();
                int xCheckInt = 0;
                int yCheckInt = 0;
                int zCheckInt = 0;
                int checkerResult = 0;
                if (dirListing != null) {
                    for (File generatorList : dirListing) {

                        FileConfiguration configCheck = YamlConfiguration.loadConfiguration(generatorList);

                        String fill = configCheck.getString("area");
                        String xCheck = configCheck.getString(fill + "-values.x");
                        String yCheck = configCheck.getString(fill + "-values.y");
                        String zCheck = configCheck.getString(fill + "-values.z");
                        xCheckInt = Integer.parseInt(xCheck);
                        yCheckInt = Integer.parseInt(yCheck);
                        zCheckInt = Integer.parseInt(zCheck);

                        if (Math.abs(xCheckInt - x) <= 1 && Math.abs(yCheckInt - y) <= 1 && Math.abs(zCheckInt - z) <= 1) {
                            checkerResult = 1;
                        }
                    }
                }

                File f = new File("plugins/Quartiers/Generators", a + ".yml");

                if (f.exists() || checkerResult == 1) {
                    player.sendMessage(ChatColor.RED + "This resources generator already exists, or there is already a resources generator 1 block nearby");
                } else {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(f);

                    config.set("area", a);
                    config.set(a + "-values.material", stringT);
                    config.set(a + "-values.x", integerX);
                    config.set(a + "-values.y", integerY);
                    config.set(a + "-values.z", integerZ);
                    config.set(a + "-values.world", world);
                    config.set(a + "-values.amount", amount);
                    config.set(a + "-values.time", "72000");

                    try {
                        config.save(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bukkit.getScheduler().cancelTasks(main.getInstance());
                    new quartiersLoader();
                    player.sendMessage(ChatColor.GREEN + "A new resources generator has been added");
                }
            } else {
                main.getInstance().getLogger();
            }
        }
    }

}
