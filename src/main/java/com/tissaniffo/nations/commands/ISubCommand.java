package com.tissaniffo.nations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ISubCommand {

    public String getPermission();

    public String getName();

    public String getDescription();

    public String getUsage();

    public void execute(CommandSender sender, Command command, String[] args);

}
