package com.jackbaron.deathswap.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public final class Chat {
    public static BaseComponent[] message(String text) {
        return new ComponentBuilder(">>> ").color(ChatColor.BLUE).bold(true)
                .append(text).color(ChatColor.GRAY).bold(false)
                .create();
    }

    public static BaseComponent[] errorMessage(String text) {
        return new ComponentBuilder(">>> ").color(ChatColor.RED).bold(true)
                .append(text).color(ChatColor.GRAY).bold(false)
                .create();
    }

    public static ComponentBuilder rawMessage(String text) {
        return new ComponentBuilder(">>> ").color(ChatColor.BLUE).bold(true)
                .append(text).color(ChatColor.GRAY).bold(false);
    }

    public static ComponentBuilder rawError(String text) {
        return new ComponentBuilder(">>> ").color(ChatColor.RED).bold(true)
                .append(text).color(ChatColor.GRAY).bold(false);
    }

    public static String legacyMessage(String text) {
        return org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + ">>> " +
                org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + text;
    }

    public static String legacyError(String text) {
        return org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + ">>> " +
                org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + text;
    }
}
