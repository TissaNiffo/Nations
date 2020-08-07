package com.tissaniffo.nations.commands.town;

import com.tissaniffo.nations.commands.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TownCommand implements TabExecutor {

    public static List<ISubCommand> commands = new ArrayList<>();

    public TownCommand(){
        commands.addAll(Arrays.asList(
                new TownHelp(),
                new TownCreate(),
                new TownAddResident()
        ));
    }

    public static List<ISubCommand> getCommands(){
        return commands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length <= 0){
                for(ISubCommand iSubCommand : commands){
                    if (iSubCommand.getName().equalsIgnoreCase("help")) {
                        iSubCommand.execute(sender, command, args);
                        return true;
                    }
                }
            }
            for(ISubCommand subCommand : commands){
                if(!subCommand.getName().equalsIgnoreCase(args[0])) continue;
                if (subCommand.getPermission() == null || sender.hasPermission(subCommand.getPermission())) {
                    subCommand.execute(sender, command, Arrays.copyOfRange(args, 1, args.length));
                }else sender.sendMessage("No permission");
                break;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(sender instanceof Player){
            if (args.length == 1) {
                List<String> result = new ArrayList<>();
                for(ISubCommand subCommand : commands){
                    result.add(subCommand.getName());
                }
                return result;
            }
        }
        return null;
    }
}
