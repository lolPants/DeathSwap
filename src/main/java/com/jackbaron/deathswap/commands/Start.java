package com.jackbaron.deathswap.commands;

import com.jackbaron.deathswap.Plugin;
import com.jackbaron.deathswap.utils.Chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public final class Start extends BaseCommand {
    public Start() {
        super("start", "deathswap.start");
    }

    @Override
    public boolean onExecuted(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if (Plugin.manager.IsStarted()) {
            sender.sendMessage(Chat.errorMessage("DeathSwap is already started!"));
            return true;
        }

        if (args.length > 0) {
            try {
                int delay = Integer.parseInt(args[0], 10);
                if (delay < 20) {
                    sender.sendMessage(Chat.errorMessage("Delay must be 20 seconds or greater."));
                    return true;
                }

                Plugin.manager.Start(delay);
            } catch (NumberFormatException ex) {
                sender.sendMessage(Chat.errorMessage("Invalid delay \"" + args[0] + "\", expected integer."));
                return true;
            }
        } else {
            Plugin.manager.Start();
        }

        String msg = "DeathSwap started!";
        Plugin.instance.getServer().broadcast(Chat.message(msg));
        Plugin.logger.info(msg);

        return true;
    }
}
