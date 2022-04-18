package io.github.thelordman.costrength.economy;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public final class EconomyManager {
    public static final HashMap<UUID, Float> balance = new HashMap<>();
    public static HashMap<UUID, Integer> killstreak = new HashMap<>();

    public static Float getBalance(Player player) {
        return balance.get(player.getUniqueId()) == null ? 0f : balance.get(player.getUniqueId());
    }

    public static void setBalance(Player player, Float amount) {
        balance.put(player.getUniqueId(), amount);
    }

    public static Integer getKillstreak(Player player) {
        return killstreak.get(player.getUniqueId()) == null ? 0 : killstreak.get(player.getUniqueId());
    }

    public static void setKillstreak(Player player, Integer amount) {
        killstreak.put(player.getUniqueId(), amount);
    }
}
