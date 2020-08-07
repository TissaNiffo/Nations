package com.tissaniffo.nations.object;

import com.tissaniffo.nations.Nations;
import org.bukkit.entity.Player;

import java.util.UUID;


public class Resident {

    private Player player;
    private UUID uuid;
    private String name;
    private Town town = null;

    public Resident(Player player, Town town){
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.town = town;
        Nations.getInstance().getCommonUtilities().getResidentDatabase().createResident(this, town);
    }

    public Resident(Player player){
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        Nations.getInstance().getCommonUtilities().getResidentDatabase().createResident(this, town);
    }

    public Player getPlayer(){
        return player;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
       this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) throws NullPointerException {
        this.town = town;
    }
}
