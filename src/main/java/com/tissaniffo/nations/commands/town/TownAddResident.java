package com.tissaniffo.nations.commands.town;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.commands.ISubCommand;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TownAddResident implements ISubCommand {

    @Override
    public String getPermission() {
        return "nations.commands.town.resident.add";
    }

    @Override
    public String getName() {
        return "resident";
    }

    @Override
    public String getDescription() {
        return "Adds a resident";
    }

    @Override
    public String getUsage() {
        return "town resident add <player> <town>";
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("Not enough args");
            return;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Resident resident = Nations.getInstance().getCommonUtilities().getResident(args[1]);
            Town town;
            if(!Nations.getInstance().getCommonUtilities().getTowns().containsKey(args[2])){
                player.sendMessage(args[2] + " isn't a town");
                return;
            }
            town = Nations.getInstance().getCommonUtilities().getTown(args[2]);
            resident.setTown(town);
            player.sendMessage(resident.getName() + "is now a resident of town " + town.getName());
        }
    }

}
