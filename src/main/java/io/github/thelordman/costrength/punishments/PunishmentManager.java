package io.github.thelordman.costrength.punishments;

import io.github.thelordman.costrength.utilities.data.DataManager;

import java.util.ArrayList;
import java.util.UUID;

public class PunishmentManager {
    private static ArrayList<Punishment> getPunishments(UUID uuid) {
        return DataManager.getPlayerData(uuid).getPunishments();
    }

    public static ArrayList<Punishment> getPunishments(UUID uuid, PunishmentType punishmentType) {
        if (getPunishments(uuid) == null) return null;
        if (getPunishments(uuid).isEmpty()) return null;
        ArrayList<Punishment> list = new ArrayList<>();
        getPunishments(uuid).forEach((p) -> {
            if (p.getPunismentType() == punishmentType) list.add(p);
        });
        return list;
    }

    public static int getPunishmentAmount(UUID uuid, PunishmentType punishmentType) {
        return getPunishments(uuid, punishmentType) == null ? 0 : getPunishments(uuid, punishmentType).size();
    }

    public static void addPunishment(UUID uuid, Punishment punishment) {
        DataManager.getPlayerData(uuid).addPunishment(punishment);
    }

    public static void removePunishment(UUID uuid, int ID) {
        DataManager.getPlayerData(uuid).removePunishment(ID);
    }

    public static boolean isMuted(UUID uuid) {
        return DataManager.getPlayerData(uuid).isMuted();
    }

    public static void setMuted(UUID uuid, long time) {
        DataManager.getPlayerData(uuid).setMuted(time);
    }
}