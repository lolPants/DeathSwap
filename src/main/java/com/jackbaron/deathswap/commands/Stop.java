package com.jackbaron.deathswap.commands;

import com.jackbaron.deathswap.Plugin;
import com.jackbaron.deathswap.utils.Chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public final class Stop extends BaseCommand {
    public Stop() {
        super("stop", "deathswap.stop");
    }

    @Override
    public boolean onExecuted(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if (!Plugin.manager.IsStarted()) {
            sender.sendMessage(Chat.errorMessage("DeathSwap is not running!"));
            return true;
        }

        Plugin.manager.Stop();

        String msg = "DeathSwap stopped!";
        Plugin.instance.getServer().broadcast(Chat.message(msg));
        Plugin.logger.info(msg);

        return true;
    }
}
