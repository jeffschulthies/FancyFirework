package de.fanta.fancyfirework.fireworks.defaults;

import de.fanta.fancyfirework.FancyFirework;
import de.iani.cubesideutils.bukkit.items.CustomHeads;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class FireWorkFountainOrange extends FireWorkFountain {

    private final FancyFirework plugin = FancyFirework.getPlugin();

    public FireWorkFountainOrange() {
        super(new NamespacedKey(FancyFirework.getPlugin(), "fountain_orange"));
    }

    @Override
    protected ItemStack createItemStack() {
        ItemStack itemStack = CustomHeads.createHead(UUID.fromString("24f265b4-119d-4a1e-a72c-01e34150bf44"), "Firework Rocket (black / orange)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzVmZDRhMmI0NzY4NmRhZTgyYTNiZWVlOTRhMzI1NGFjYTJlODQyMzdmZGY2ZjgyMDQzZTU3YWE3OGMzYzYyIn19fQ==");
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.of("#fe8a06") + "Orange Fountain");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public Color randomColor() {
        java.awt.Color color = java.awt.Color.getHSBColor(0.07f, random.nextFloat(0.5f, 1), random.nextFloat(0.5f, 1));
        return Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }

}


