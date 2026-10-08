package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * AutoArmor: automatically equips the best armor found in the inventory.
 */
public class AutoArmor extends Module {

    public AutoArmor() {
        super("AutoArmor", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        // Armor inventory slots: 36=head, 37=chest, 38=legs, 39=feet.
        for (int slot = 36; slot <= 39; slot++) {
            EquipmentSlot equipmentSlot = armorSlotFor(slot);
            ItemStack current = player.getInventory().getItem(slot);
            int bestDefense = current.getItem() instanceof ArmorItem armorItem ? armorItem.getDefense() : -1;
            int bestSlot = -1;

            for (int i = 0; i < 36; i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == equipmentSlot) {
                    int defense = armorItem.getDefense();
                    if (defense > bestDefense) {
                        bestDefense = defense;
                        bestSlot = i;
                    }
                }
            }

            if (bestSlot >= 0) {
                player.getInventory().setItem(slot, player.getInventory().getItem(bestSlot));
                player.getInventory().setItem(bestSlot, current);
            }
        }
    }

    private static EquipmentSlot armorSlotFor(int slot) {
        return switch (slot) {
            case 36 -> EquipmentSlot.HEAD;
            case 37 -> EquipmentSlot.CHEST;
            case 38 -> EquipmentSlot.LEGS;
            default -> EquipmentSlot.FEET;
        };
    }
}
