package io.github.thelordman.costrength.items;

import io.github.thelordman.costrength.utilities.Methods;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {
    public static void joinKit(Player player) {
        ItemStack[] items = {new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_SWORD), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.IRON_PICKAXE)};
        items[6].editMeta(meta -> meta.setDisplayName(Methods.cStr("&7Backup Pickaxe")));
        for (ItemStack item : items) item.editMeta(meta -> meta.setUnbreakable(true));

        player.getInventory().setHelmet(items[0]);
        player.getInventory().setChestplate(items[1]);
        player.getInventory().setLeggings(items[2]);
        player.getInventory().setBoots(items[3]);
        player.getInventory().setItem(0, items[4]);
        player.getInventory().setItem(1, items[5]);
        player.getInventory().setItem(9, items[6]);
    }

    public static boolean isFromKit(ItemStack item) {
        return item.getItemMeta().isUnbreakable();
    }
}