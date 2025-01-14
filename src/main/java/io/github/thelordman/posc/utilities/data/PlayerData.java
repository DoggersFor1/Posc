package io.github.thelordman.posc.utilities.data;

import io.github.thelordman.posc.punishments.Punishment;
import io.github.thelordman.posc.utilities.Rank;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerData implements Serializable {
    @Serial
    private static final long serialVersionUID = 3022193081975011242L;

    private final UUID uuid;

    private String address;

    private double balance = 0d;
    private double bounty = 0d;
    private double xp = 0d;
    private int level = 1;
    private int killStreak = 0;
    private boolean staffMode = false;
    private Integer muted = 0;

    private Rank rank = Rank.DEFAULT;
    private final ArrayList<Punishment> punishments = new ArrayList<>();

    public PlayerData(UUID uuid, String address) {
        this.uuid = uuid;
        this.address = address;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKillStreak() {
        return killStreak;
    }

    public void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean inStaffMode() {
        return staffMode;
    }

    public void setStaffMode(boolean staffMode) {
        this.staffMode = staffMode;
    }

    public ArrayList<Punishment> getPunishments() {
        return punishments;
    }

    public void addPunishment(Punishment punishment) {
        this.punishments.add(punishment);
    }

    public boolean removePunishment(int ID) {
        for (Punishment punishment : punishments) {
            if (punishment.getID() == ID) {
                punishments.remove(punishment);
                return true;
            }
        }
        return false;
    }

    public boolean isMuted() {
        if (muted == null) return true;
        return muted > Instant.now().getEpochSecond();
    }

    public void setMuted(Integer muted) {
        this.muted = muted;
    }
}