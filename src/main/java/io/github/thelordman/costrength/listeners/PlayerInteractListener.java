package io.github.thelordman.costrength.listeners;

import io.github.thelordman.costrength.utilities.Data;
import io.github.thelordman.costrength.utilities.GUIHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!Objects.equals(event.getHand(), EquipmentSlot.HAND) | !event.getAction().isRightClick() | !event.getItem().getItemMeta().isUnbreakable()) return;
        if (event.getItem().getType().equals(Material.BOW) && !event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        GUIHandler.openGUI(Data.GUIs[2], event.getPlayer());
    }
}