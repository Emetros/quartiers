package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class ressourceGeneratorLoader {
    public ressourceGeneratorLoader(String stringT, String amount, String integerX, String integerY, String integerZ, String time, String world) {

        int x = Integer.parseInt(integerX);
        int y = Integer.parseInt(integerY);
        int z = Integer.parseInt(integerZ);
        int q = Integer.parseInt(amount);
        int timeInt = Integer.parseInt(time);
        int test = 1;

        stringT = stringT.toUpperCase();

        Material t = Material.getMaterial(stringT);
        if (t != null){
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            ItemStack i = new ItemStack(t, q);
            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);
            Chest c = (Chest) b.getState();
            if(b.getType() == Material.CHEST) {
                scheduler.scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        c.getBlockInventory().addItem(i);
                    }
                }, 1, timeInt);
            } else {
                main.getInstance().getLogger();
            }
        }
    }
}
