package io.github.thelordman.costrength.listeners;

import io.github.thelordman.costrength.CoStrength;
import io.github.thelordman.costrength.scoreboard.ScoreboardHandler;
import io.github.thelordman.costrength.economy.EconomyManager;
import io.github.thelordman.costrength.utilities.Methods;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = event.getPlayer().hasPlayedBefore() ? "&7[&a+&7] &7" + event.getPlayer().getDisplayName() : "&7[&a+&7] &7" + event.getPlayer().getDisplayName() + " &6#" + Bukkit.getServer().getOfflinePlayers().length;
        event.setJoinMessage(Methods.cStr(message));

        if (EconomyManager.getBalance(event.getPlayer()) == null) {
            EconomyManager.setBalance(event.getPlayer(), 0f);
        }
        if (EconomyManager.getKillstreak(event.getPlayer()) == null) {
            EconomyManager.setKillstreak(event.getPlayer(), 0);
        }

        ScoreboardHandler.updateBoard(event.getPlayer());

        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getPlayer().getDisplayName() + " Joined", null, "https://crafatar.com/avatars/" + event.getPlayer().getUniqueId());
        builder.setColor(Color.GREEN);

        CoStrength.minecraftChatChannel.sendMessageEmbeds(builder.build()).queue();

    }
}
