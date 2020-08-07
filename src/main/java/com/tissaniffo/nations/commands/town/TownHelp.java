package com.tissaniffo.nations.commands.town;

import com.tissaniffo.nations.commands.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TownHelp implements ISubCommand {

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Town help";
    }

    @Override
    public String getUsage() {
        return "town help";
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        StringBuilder help = new StringBuilder();
        help.append("----------------------\n");
        for(ISubCommand subCommand : TownCommand.getCommands()){
            help.append("/").append(subCommand.getUsage()).append(" - ").append(subCommand.getDescription()).append("\n");
        }
        sender.sendMessage(help.toString());
    }
}
