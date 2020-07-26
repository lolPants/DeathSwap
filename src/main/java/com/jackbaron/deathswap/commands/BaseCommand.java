package com.jackbaron.deathswap.commands;

import com.jackbaron.deathswap.utils.Chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor {
    public String name;
    public String[] aliases;

    @Nullable
    private String permission;

    BaseCommand(String name, @Nullable String permission, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.permission = permission;
    }

    public boolean canUseCommand(@NotNull CommandSender sender) {
        return permission == null || sender.hasPermission(permission);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if (!canUseCommand(sender)) {
            sender.spigot().sendMessage(Chat.errorMessage("You do not have permission to do that!"));
            return true;
        }

        return onExecuted(sender, command, name, args);
    }

    public abstract boolean onExecuted(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args);

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
