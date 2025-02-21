package io.github.thelordman.posc.listeners;

import io.github.thelordman.posc.discord.Discord;
import io.github.thelordman.posc.utilities.Methods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.getScoreboardTags().contains("commandSpyToggled") && event.getPlayer() != online) {
                online.sendMessage(Methods.cStr("&f" + event.getPlayer().getDisplayName() + " &6executed the command &f" + event.getMessage() + "&6."));
            }
        }
        if (event.getMessage().contains("@")) {
            Discord.commandLogChannel.sendMessage(Methods.replaceColorCodes("<t:" + System.currentTimeMillis() / 1000 + ":F> **" + event.getPlayer().getDisplayName() + "** executed the command `/I'm a fat bitch`", '§')).queue();
        }
        else {
            Discord.commandLogChannel.sendMessage("<t:" + System.currentTimeMillis() / 1000 + ":F> **" + Methods.replaceColorCodes(event.getPlayer().getDisplayName(), '§') + "** executed the command `" + event.getMessage() + "`").queue();
        }
    }
}