package com.jackbaron.deathswap.commands;

import com.jackbaron.deathswap.Plugin;
import com.jackbaron.deathswap.utils.Chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import org.jetbrains.annotations.NotNull;

public final class About implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if (!sender.hasPermission("deathswap.about")) {
            sender.spigot().sendMessage(Chat.errorMessage("You do not have permission to do that!"));
            return true;
        }

        PluginDescriptionFile desc = Plugin.instance.getDescription();
        CommandSender.Spigot spigot = sender.spigot();

        spigot.sendMessage(Chat.rawMessage("").append("DeathSwap v" + desc.getVersion()).bold(true).create());

        TextComponent website = new TextComponent(desc.getWebsite());
        website.setItalic(true);
        website.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, desc.getWebsite()));

        spigot.sendMessage(Chat.rawMessage("").append("By " + String.join(",", desc.getAuthors()) + " - ").append(website).create());
        spigot.sendMessage(Chat.message("---------------------"));

        String description = desc.getDescription();
        assert description != null;

        String[] lines = description.split("\\r?\\n");
        for (String line : lines) {
            spigot.sendMessage(Chat.message(line));
        }

        return true;
    }
}
