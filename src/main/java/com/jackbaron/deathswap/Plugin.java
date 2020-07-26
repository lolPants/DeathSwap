package com.jackbaron.deathswap;

import com.jackbaron.deathswap.commands.*;
import com.jackbaron.deathswap.utils.Chat;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public final class Plugin extends JavaPlugin {
    public static JavaPlugin instance;
    public static Logger logger;
    public static DeathSwap manager;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        manager = new DeathSwap();

        PluginCommand mainCommand = Objects.requireNonNull(getCommand("deathswap"));
        CommandManager.registerDefaultCommand(new About());
        CommandManager.registerSubCommand(new Start());
        CommandManager.registerSubCommand(new Stop());
        CommandManager.registerSubCommand(new Force());

        mainCommand.setExecutor((sender, command, name, args) -> {
            if (args.length == 0) {
                CommandExecutor def = CommandManager.getDefaultCommand();

                if (def != null) {
                    return def.onCommand(sender, command, name, args);
                } else {
                    sender.spigot().sendMessage(Chat.errorMessage("That command does not exist!"));
                    return true;
                }
            }

            String arg = args[0].toLowerCase();
            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

            BaseCommand cmd = CommandManager.getCommand(arg);
            if (cmd != null) {
                return cmd.onCommand(sender, command, arg, newArgs);
            }

            sender.spigot().sendMessage(Chat.errorMessage("That command does not exist!"));
            return true;
        });

        mainCommand.setTabCompleter((sender, command, name, args) -> {
            if (args.length > 2) {
                return new ArrayList<>();
            } else if (args.length > 1) {
                BaseCommand cmd = CommandManager.getCommand(args[0]);
                if (cmd == null) {
                    return new ArrayList<>();
                }

                String arg = args[0].toLowerCase();
                String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

                return cmd.onTabComplete(sender, command, arg, newArgs);
            }

            ArrayList<String> commands = new ArrayList<>();
            for (Map.Entry<String, BaseCommand> entry : CommandManager.getCommands().entrySet()) {
                BaseCommand cmd = entry.getValue();
                if (!cmd.canUseCommand(sender)) {
                    continue;
                }

                commands.add(cmd.name);
                commands.addAll(Arrays.asList(cmd.aliases));
            }

            return commands;
        });
    }
}
