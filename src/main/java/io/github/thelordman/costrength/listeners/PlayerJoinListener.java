package io.github.thelordman.costrength.listeners;

import io.github.thelordman.costrength.CoStrength;
import io.github.thelordman.costrength.commands.VanishCommand;
import io.github.thelordman.costrength.discord.Discord;
import io.github.thelordman.costrength.economy.LevelHandler;
import io.github.thelordman.costrength.ranks.RankManager;
import io.github.thelordman.costrength.scoreboard.ScoreboardHandler;
import io.github.thelordman.costrength.economy.EconomyManager;
import io.github.thelordman.costrength.items.Kit;
import io.github.thelordman.costrength.utilities.Methods;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (Player vanishedPlayer : VanishCommand.vanishedPlayers) {
            if (event.getPlayer().equals(vanishedPlayer)) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (RankManager.permissionLevel(onlinePlayer) < RankManager.permissionLevel(event.getPlayer())) {
                        onlinePlayer.hidePlayer(CoStrength.get(), event.getPlayer());
                    }
                }
                event.joinMessage(null);
                event.getPlayer().sendMessage(Methods.cStr("&6You are currently in vanish."));
                continue;
            }
            if (RankManager.permissionLevel(event.getPlayer()) < RankManager.permissionLevel(vanishedPlayer)) {
                event.getPlayer().hidePlayer(CoStrength.get(), vanishedPlayer);
            }
        }

        if (!event.getPlayer().hasPlayedBefore()) Kit.joinKit(event.getPlayer());

        EconomyManager.setBalance(event.getPlayer().getUniqueId(), (double) event.getPlayer().getStatistic(Statistic.USE_ITEM, Material.GOLD_NUGGET));
        EconomyManager.setBounty(event.getPlayer().getUniqueId(), (double) event.getPlayer().getStatistic(Statistic.USE_ITEM, Material.SPYGLASS));
        EconomyManager.setXp(event.getPlayer().getUniqueId(), (double) event.getPlayer().getStatistic(Statistic.USE_ITEM, Material.EXPERIENCE_BOTTLE));
        EconomyManager.setLevel(event.getPlayer().getUniqueId(), event.getPlayer().getStatistic(Statistic.USE_ITEM, Material.FIREWORK_ROCKET));
        EconomyManager.setKillstreak(event.getPlayer().getUniqueId(), event.getPlayer().getStatistic(Statistic.USE_ITEM, Material.WOODEN_SWORD));

        if (EconomyManager.getLevel(event.getPlayer().getUniqueId()) == 0) EconomyManager.setLevel(event.getPlayer().getUniqueId(), 1);
        LevelHandler.xp(event.getPlayer());

        Methods.updateDisplayName(event.getPlayer());
        event.getPlayer().setPlayerListHeaderFooter(Methods.cStr("\n  &6&lCoStrength.minehut.gg  \n"), Methods.cStr("\n&6/help &8| &6/dc &8| &6/buy"));

        String message = event.getPlayer().hasPlayedBefore() ? "&7[&a+&7] &7" + event.getPlayer().getDisplayName() : "&7[&a+&7] &7" + event.getPlayer().getDisplayName() + " &6#" + Methods.rStr((double) Bukkit.getServer().getOfflinePlayers().length);
        event.setJoinMessage(Methods.cStr(message));

        ScoreboardHandler.updateBoard(event.getPlayer());

        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(Methods.replaceColorCodes(event.getPlayer().getDisplayName() + " Joined", '§'), null, "https://crafatar.com/avatars/" + event.getPlayer().getUniqueId());
        builder.setColor(Color.GREEN);

        Discord.minecraftChatChannel.sendMessageEmbeds(builder.build()).queue();
    }
}