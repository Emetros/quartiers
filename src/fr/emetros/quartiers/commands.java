package fr.emetros.quartiers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class commands implements CommandExecutor {
    private final main plugin;

    public commands(main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("quartierscreate")){
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
            if(commandSender instanceof Player) {
                Player player = (Player) commandSender;

                if(player.hasPermission("quartiers.list") || player.isOp()) {
                    new quartiersList(player);
                }
            }
        }

        if(command.getName().equalsIgnoreCase("quartiersdisable")) {
            if(commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if(player.hasPermission("quartiers.disable") || player.isOp()) {
                    if(strings.length == 1) {
                        File file = new File("plugins/Quartiers/Generators", strings[0] + ".yml");
                        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                        if(file.exists()) {
                            String areaName = strings[0];
                            config.set(areaName + "-values.isEnabled", 0);
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            main.getInstance().saveConfig();
                            Bukkit.getScheduler().cancelTasks(main.getInstance());
                            new quartiersLoader();
                            player.sendMessage(ChatColor.GREEN + strings[0] + " is now disabled");
                        }
                    }
                    if(strings.length > 1) {
                        player.sendMessage("Too many arguments !");
                    }
                    if(strings.length < 1) {
                        player.sendMessage("Not enough arguments !");
                    }
                }
            }
        }

        if(command.getName().equalsIgnoreCase("quartiersenable")) {
            if(commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if(player.hasPermission("quartiers.enable")) {
                    if(strings.length == 1) {
                        File file = new File("plugins/Quartiers/Generators", strings[0] + ".yml");
                        if(file.exists()) {
                            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                            String areaName = strings[0];
                            config.set(areaName + "-values.isEnabled", 1);
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Bukkit.getScheduler().cancelTasks(main.getInstance());
                            new quartiersLoader();
                            player.sendMessage(ChatColor.GREEN + strings[0] + " is now enabled");
                        } else {
                            player.sendMessage("This generator doesn't exists");
                        }
                    }
                    if(strings.length > 1) {
                        player.sendMessage("Too many arguments !");
                    }
                    if(strings.length < 1) {
                        player.sendMessage("Not enough arguments !");
                    }
                }
            }
        }

        return false;
    }
}
