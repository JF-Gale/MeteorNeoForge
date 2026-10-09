package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * Enchant: apply an enchantment of any level to the item in hand.
 * Usage: .enchant <enchantment> [level]   (level default 255)
 * Example: .enchant sharpness 1000  .enchant protection 32767
 * The level limit of vanilla anvils is ignored.
 */
public class Enchant extends Module {

    public Enchant() {
        super("Enchant", Category.PLAYER);
    }

    /** Apply the enchantment to the main-hand item. Returns false on failure. */
    public static boolean apply(Minecraft mc, String arg) {
        if (mc.player == null || mc.level == null) {
            return false;
        }
        String[] parts = arg.split(" ");
        if (parts.length < 1 || parts[0].isEmpty()) {
            return false;
        }
        String enchName = parts[0];
        int level = 255;
        if (parts.length >= 2) {
            try {
                level = Integer.parseInt(parts[1]);
            } catch (NumberFormatException ignored) {
                level = 255;
            }
        }
        ResourceLocation loc = ResourceLocation.tryParse(enchName);
        if (loc == null) {
            loc = ResourceLocation.tryParse("minecraft:" + enchName.toLowerCase());
        }
        if (loc == null) {
            return false;
        }
        Registry<Enchantment> reg = mc.level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        ResourceKey<Enchantment> key = ResourceKey.create(Registries.ENCHANTMENT, loc);
        Holder.Reference<Enchantment> holder;
        try {
            holder = reg.getHolderOrThrow(key);
        } catch (Exception e) {
            return false;
        }
        ItemStack stack = mc.player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }
        stack.enchant(holder, level);
        return true;
    }
}
