package io.github.thelordman.posc.commands;

import io.github.thelordman.posc.utilities.CommandName;
import io.github.thelordman.posc.utilities.Methods;
import io.github.thelordman.posc.utilities.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandName("clearchat")
public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!Rank.hasPermission(sender, (byte) 4)) return true;
        String executor = sender instanceof Player ? ((Player) sender).getDisplayName() : "Console";

        for (int i = 0; i < 200; i++) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage(Methods.cStr("&f" + executor + " &6cleared the chat."));

        return true;
    }
}
