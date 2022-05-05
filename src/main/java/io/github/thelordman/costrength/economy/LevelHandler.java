package io.github.thelordman.costrength.economy;

import io.github.thelordman.costrength.discord.Discord;
import io.github.thelordman.costrength.utilities.Methods;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class LevelHandler {
    public static Float xpRequirement(UUID uuid) {
        int l = EconomyManager.getLevel(uuid);
        return (float) l * 1000;
    }
    public static void xp(Player player) {
        if (EconomyManager.getXp(player.getUniqueId()) >= xpRequirement(player.getUniqueId())) {
            EconomyManager.setLevel(player.getUniqueId(), EconomyManager.getLevel(player.getUniqueId()) + 1);
            EconomyManager.setXp(player.getUniqueId(), 0f);
            player.setLevel(EconomyManager.getLevel(player.getUniqueId()));
            player.setExp(0);
            Methods.updateDisplayName(player);

            if (List.of(10, 25, 50, 75, 100, 150, 200, 300, 400, 500, 600, 700, 800, 900, 1000).contains(EconomyManager.getLevel(player.getUniqueId()))) {
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                Bukkit.broadcastMessage(Methods.cStr(player.getDisplayName() + " &6has reached level &f" + EconomyManager.getLevel(player.getUniqueId()) + "&6."));
                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor(Methods.replaceColorCodes(player.getDisplayName(), '§'), null, "https://crafatar.com/avatars/" + player.getUniqueId());
                builder.setColor(Color.CYAN);
                builder.setDescription(Methods.replaceColorCodes(player.getDisplayName() + " has reached level " + EconomyManager.getLevel(player.getUniqueId()), '§'));

                Discord.minecraftChatChannel.sendMessageEmbeds(builder.build()).queue();
            }
            else player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }
        else {
            player.setExp(EconomyManager.getXp(player.getUniqueId()) / xpRequirement(player.getUniqueId()));
        }
    }
}