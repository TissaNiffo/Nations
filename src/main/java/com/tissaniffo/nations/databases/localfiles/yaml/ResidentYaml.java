package com.tissaniffo.nations.databases.localfiles.yaml;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.databases.IDatabaseResident;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResidentYaml implements IDatabaseResident {

    private File location = new File(Nations.getInstance().getDataFolder() + File.separator + "residents");
    private Map<Resident, FileConfiguration> config = new HashMap<>();

    @Override
    public Boolean createResident(Resident resident, Town town) {
        File residentFileLoc = new File(location, resident.getUuid().toString() + ".yml");
        FileConfiguration data = null;
        if (!location.exists()) {
            if (!location.mkdirs()) {
                Nations.getInstance().getLogger().warning(resident.getUuid().toString() + "'s file couldn't be created, please contact with the developer.");
            }
        }
        if (!existsResident(resident)) {
            try{
                if (residentFileLoc.createNewFile()) {
                    data = YamlConfiguration.loadConfiguration(residentFileLoc);
                    data.set("uuid", resident.getUuid().toString());
                    data.set("name", resident.getName());
                    data.set("town", town.getName());
                    data.save(residentFileLoc);
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (data == null) {
            data = YamlConfiguration.loadConfiguration(residentFileLoc);
        }
        resident.setName(data.getString("name"));
        resident.setUuid(UUID.fromString(data.getString("uuid")));
        resident.setTown(Nations.getInstance().getCommonUtilities().getTown(data.getString("town")));
        config.put(resident, data);
        Nations.getInstance().getCommonUtilities().addResident(resident);
        return true;
    }

    @Override
    public Boolean existsResident(com.tissaniffo.nations.object.Resident resident) {
        return new File(location, resident.getUuid().toString()).exists();
    }

    @Override
    public Boolean saveResident(com.tissaniffo.nations.object.Resident resident) {
        if (config.containsKey(resident) && existsResident(resident)) {
            FileConfiguration data = config.get(resident);
            if (data != null) {
                try {
                    data.set("uuid", resident.getUuid());
                    data.set("name", resident.getName());
                    data.set("town", resident.getTown().getName());
                    data.save(new File(location, resident.getUuid().toString() + ".yml"));
                    return true;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
