package com.jackbaron.deathswap;

import com.jackbaron.deathswap.utils.Chat;
import com.jackbaron.deathswap.utils.Rand;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class DeathSwap {
    private static final int DELAY = 300;
    private BukkitTask task;
    private Boolean force = false;

    public void Start() {
        Start(DELAY);
    }

    public void Start(final int delay) {
        task = (new BukkitRunnable() {
            int timer = delay;

            @Override
            public void run() {
                if (force) {
                    timer = 10;
                    force = false;
                }

                if (timer <= 10 && timer != 0) {
                    String msg = "Swapping in " + timer + (timer == 1 ? " second!" : " seconds!");
                    Plugin.instance.getServer().broadcast(Chat.message(msg));
                }

                if (timer == 0) {
                    SwapPlayers();
                    timer = delay;
                } else {
                    timer--;
                }
            }

        }).runTaskTimer(Plugin.instance, 0L, 20L);
    }

    public void Stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public Boolean IsStarted() {
        return task != null;
    }

    public void Force() {
        force = true;
    }

    private void SwapPlayers() {
        ArrayList<? extends Player> players = Plugin.instance.getServer()
                .getOnlinePlayers().stream()
                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
                .collect(Collectors.toCollection(ArrayList::new));

        if (players.size() < 2) return;
        ArrayList<? extends Player> shuffled = Rand.shuffle(players);

        HashMap<Player, Location> positions = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            Player target = players.get(i);
            Player other = shuffled.get(i);

            positions.put(target, other.getLocation());
        }

        for (Map.Entry<Player, Location> entry : positions.entrySet()) {
            entry.getKey().teleport(entry.getValue());
        }
    }
}
