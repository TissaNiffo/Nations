package com.tissaniffo.nations.databases.localfiles.yaml;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.databases.IDatabaseTown;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import com.tissaniffo.nations.utils.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownYaml implements IDatabaseTown {

    private File location = new File(Nations.getInstance().getDataFolder() + File.separator + "towns");
    private Map<Town, FileConfiguration> config = new HashMap<>();
    private List<String> residents = new ArrayList<>();

    @Override
    public Boolean createTown(Town town, String name, Resident major) {
        File townFileLoc = new File(location, name + ".yml");
        FileConfiguration data = null;
        if (!location.exists()) {
            if (!location.mkdirs()) {
                Nations.getInstance().getLogger().warning(name + "'s file couldn't be created, please contact with the developer.");
            }
        }
        if (!existsTown(town)) {
            try{
                if (townFileLoc.createNewFile()) {
                    data = YamlConfiguration.loadConfiguration(townFileLoc);
                    data.set("name", name);
                    data.set("residentAmount", town.getResidentAmount());
                    data.set("residents", major.getName());
                    for(Resident resident : town.getResidents()){
                        data.set("residents", resident.getName());
                    }
                    data.save(townFileLoc);
                }else{
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (data == null) {
            data = YamlConfiguration.loadConfiguration(townFileLoc);
        }
        town.setName(data.getString("name"));
        town.setResidentAmount(data.getInt("residentAmount"));
        for(int i = 0; i < data.getStringList("residents").size(); i++){
            town.addResident(Nations.getInstance().getCommonUtilities().getResident(data.getStringList("residents").get(i)));
        }
        town.setMajor(Nations.getInstance().getCommonUtilities().getResident(data.getString("major")));
        config.put(town, data);
        Nations.getInstance().getCommonUtilities().addTown(town);
        return true;
    }

    @Override
    public Boolean existsTown(Town town) {
        return new File(location, town.getName() + ".yml").exists();
    }

    @Override
    public Boolean saveTown(Town town) {
        if (config.containsKey(town) && existsTown(town)) {
            FileConfiguration data = config.get(town);
            if (data != null) {
                try{
                    data.set("name", town.getName());
                    data.set("residentAmount", town.getResidentAmount());
                    for(Resident resident : town.getResidents()){
                        residents.add(resident.getName());
                    }
                    data.set("residentAmount", residents);
                    data.set("major", town.getMajor().getName());
                    data.save(new File(location, town.getName() + ".yml"));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Boolean loadTowns() {
        if (location.exists()) {
            File[] files = location.listFiles();
            for(File file : files){
                FileConfiguration data = YamlConfiguration.loadConfiguration(file);
                Resident major = Nations.getInstance().getCommonUtilities().getResident(data.getString("major"));
                Town town = new Town(FileUtils.getFileNameWithoutExtension(file), major);
                return true;
            }
        }
        return false;
    }
}
