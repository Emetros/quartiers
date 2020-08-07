package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;


public class commands implements CommandExecutor {
    private final main plugin;

    public commands(main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("createquartier")){
            if(commandSender instanceof Player) {

                Player player = (Player) commandSender;

                if(player.hasPermission("quartiers.createquartier")) {
                    if (strings.length < 6) {
                        player.sendMessage(ChatColor.RED + "Not enough arguments !");
                    }
                    if (strings.length > 7) {
                        player.sendMessage(ChatColor.RED + "Too many arguments !");
                    }
                    if (strings.length == 7) {
                        World w = player.getWorld();
                        String world = w.getName();
                        new quartiersCreate(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], world, player);
                    }
                    if (strings.length == 6) {
                        World w = player.getWorld();
                        String world = w.getName();
                        new quartiersCreate(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], world, player);
                    }
                } else {
                }
                return false;
            }
        }

        if(command.getName().equalsIgnoreCase("quartiersreload")) {
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(player.hasPermission("quartiers.reload") || player.isOp()) {
                    Bukkit.getScheduler().cancelTasks(main.getInstance());
                    new quartiersLoader();
                    player.sendMessage(ChatColor.GREEN + "Quartiers reloaded");
                }
            } else {
            }
        }
        if(command.getName().equalsIgnoreCase("quartierslist")) {
            if(strings[0].equalsIgnoreCase("list")) {
                if(commandSender instanceof Player) {
                    Player player = (Player) commandSender;

                    if(player.hasPermission("quartiers.list") || player.isOp()) {
                        File folder = new File("plugins/Quartiers/Generators");
                        File[] dirListing = folder.listFiles();
                        if(dirListing != null) {
                            player.sendMessage("" + dirListing);
                        }
                    }
                }
            }
        }

        return false;
    }
}
