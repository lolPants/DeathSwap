package com.jackbaron.deathswap.commands;

import org.bukkit.command.CommandExecutor;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class CommandManager {
    private static CommandExecutor defaultCommand;
    private static Map<String, BaseCommand> commands = new HashMap<>();

    public static void registerSubCommand(BaseCommand command) {
        String name = command.name;
        commands.putIfAbsent(name, command);
    }

    public static void registerDefaultCommand(CommandExecutor command) {
        defaultCommand = command;
    }

    public static Map<String, BaseCommand> getCommands() {
        return commands;
    }

    public static @Nullable BaseCommand getCommand(String name) {
        BaseCommand cmd = commands.get(name);
        if (cmd != null) {
            return cmd;
        }

        for (Map.Entry<String, BaseCommand> entry : getCommands().entrySet()) {
            BaseCommand c = entry.getValue();
            if (Arrays.asList(c.aliases).contains(name)) {
                return c;
            }
        }

        return null;
    }

    public static @Nullable CommandExecutor getDefaultCommand() {
        return defaultCommand;
    }
}
