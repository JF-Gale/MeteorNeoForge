package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Give: obtain an arbitrary item by id.
 * Usage: .give <item-id> [count]
 * Examples: .give diamond 64   .give netherite_sword 1
 * If the inventory is full the item is dropped in front of the player.
 * Combine with .enchant / .name to build custom items.
 */
public class Give extends Module {

    public Give() {
        super("Give", Category.MISC);
    }

    /** Spawn the item into the inventory (or drop it). Returns false on failure. */
    public static boolean apply(Minecraft mc, String arg) {
        if (mc.player == null || mc.level == null) {
            return false;
        }
        String[] parts = arg.trim().split(" ");
        if (parts.length < 1 || parts[0].isEmpty()) {
            return false;
        }
        String itemId = parts[0];
        int count = 1;
        if (parts.length >= 2) {
            try {
                count = Integer.parseInt(parts[1]);
            } catch (NumberFormatException ignored) {
                count = 1;
            }
        }
        ResourceLocation loc = ResourceLocation.tryParse(itemId);
        if (loc == null) {
            loc = ResourceLocation.tryParse("minecraft:" + itemId.toLowerCase());
        }
        if (loc == null) {
            return false;
        }
        Item item = BuiltInRegistries.ITEM.get(loc);
        if (item == null || item == Items.AIR) {
            return false;
        }
        ItemStack stack = new ItemStack(item);
        stack.setCount(Math.max(1, Math.min(count, item.getMaxStackSize(stack))));
        if (!mc.player.getInventory().add(stack)) {
            ItemEntity entity = new ItemEntity(mc.level,
                    mc.player.getX(), mc.player.getY() + 0.5, mc.player.getZ(), stack);
            mc.level.addFreshEntity(entity);
        }
        return true;
    }
}
