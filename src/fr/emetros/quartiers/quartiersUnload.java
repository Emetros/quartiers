package fr.emetros.quartiers;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.ArrayList;

public class quartiersUnload {
    public quartiersUnload() {
        File folder = new File("plugins/Quartiers/Generators");
        File[] dirList = folder.listFiles();
        ArrayList<Integer> listTask = new ArrayList<Integer>();
        int i = 0;
        for(File listFiles: dirList) {
            i = i++;
            Bukkit.getScheduler().cancelTask(i);
        }
    }
}
