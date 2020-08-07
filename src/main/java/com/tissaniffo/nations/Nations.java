package com.tissaniffo.nations;

import com.tissaniffo.nations.commands.town.TownCommand;
import com.tissaniffo.nations.managers.ListenerManager;
import com.tissaniffo.nations.object.Town;
import com.tissaniffo.nations.utils.CommonUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Nations extends JavaPlugin {

    private static Nations nations;

    private CommonUtilities commonUtilities;
    private ListenerManager listenerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        nations = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        commonUtilities = new CommonUtilities();
        listenerManager = new ListenerManager(this);
        commonUtilities.setupCommonVariables();
        listenerManager.loadListeners();
        getCommand("town").setExecutor(new TownCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveDefaultConfig();
        for(Map.Entry<String, Town> entry : commonUtilities.getTowns().entrySet()){
            commonUtilities.getTownDatabase().saveTown(entry.getValue());
            commonUtilities.removeTown(entry.getValue());
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            commonUtilities.getResidentDatabase().saveResident(commonUtilities.getResident(player.getName()));
            commonUtilities.removeResident(player.getName());
        }
    }

    public static Nations getInstance(){
        return nations;
    }

    public CommonUtilities getCommonUtilities(){
        return commonUtilities;
    }

    public void addListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

}
