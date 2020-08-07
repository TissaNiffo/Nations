package com.tissaniffo.nations.commands.town;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.commands.ISubCommand;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TownCreate implements ISubCommand {

    @Override
    public String getPermission() {
        return "nations.commands.town.create";
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Creates a town";
    }

    @Override
    public String getUsage() {
        return "town create <name>";
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("use /town create <name>");
            return;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Nations.getInstance().getCommonUtilities().getTowns().containsKey(args[0])) {
                player.sendMessage(args[0] + " is already a town");
                return;
            }
            String name = args[0];
            Resident major = Nations.getInstance().getCommonUtilities().getResident(player.getName());
            Town town = new Town(name, major);
            player.sendMessage("You created the town " + town.getName());
            return;
        }
    }
}
