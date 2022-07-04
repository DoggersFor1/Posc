package io.github.thelordman.costrength.commands;

import io.github.thelordman.costrength.discord.Discord;
import io.github.thelordman.costrength.utilities.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LiamiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Methods.checkCommandPermission(sender, (byte) 5)) return true;
        Bukkit.broadcastMessage(Methods.cStr("&8[&71&8] &cOwner &8| &9Liami_&7: &f" + Methods.arrayToString(args)));
        Discord.minecraftChatChannel.sendMessage("**[1] Owner | Liami_:** " + Methods.arrayToString(args)).queue();
        return true;
    }
}