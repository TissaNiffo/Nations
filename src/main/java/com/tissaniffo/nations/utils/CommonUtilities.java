package com.tissaniffo.nations.utils;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.databases.IDatabaseResident;
import com.tissaniffo.nations.databases.IDatabaseTown;
import com.tissaniffo.nations.databases.localfiles.yaml.ResidentYaml;
import com.tissaniffo.nations.databases.localfiles.yaml.TownYaml;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommonUtilities extends CommonVariables {

    public void setupCommonVariables(){
        residents = new HashMap<>();
        towns = new HashMap<>();

        if (Nations.getInstance().getConfig().getString("storage").equalsIgnoreCase("yaml")) {
            residentData = new ResidentYaml();
            townData = new TownYaml();
            townData.loadTowns();
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            new Resident(player);
        }
    }

    public Resident getResident(String player) {
        return residents.get(player);
    }

    public void addResident(Resident resident){
        residents.put(resident.getPlayer().getName(), resident);
    }

    public void removeResident(String player){
        residents.remove(player);
    }

    public Map<String, Resident> getResidents(){
        return residents;
    }

    public IDatabaseResident getResidentDatabase(){
        return residentData;
    }

    public Town getTown(String name){
        return towns.get(name);
    }

    public void addTown(Town town){
        towns.put(town.getName(), town);
    }

    public void removeTown(Town town){
        towns.remove(towns);
    }

    public Map<String, Town> getTowns(){
        return towns;
    }

    public IDatabaseTown getTownDatabase(){
        return townData;
    }

}
