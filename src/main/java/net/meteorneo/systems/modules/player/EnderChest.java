package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * EnderChest: opens the ender chest screen when holding an ender chest item.
 */
public class EnderChest extends Module {

    private boolean prevHeld = false;

    public EnderChest() {
        super("EnderChest", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null) {
            return;
        }
        boolean held = isHoldingEnderChest(mc);
        if (held && !prevHeld && mc.screen == null) {
            mc.openEnderChest();
        }
        prevHeld = held;
    }

    private boolean isHoldingEnderChest(Minecraft mc) {
        ItemStack main = mc.player.getMainHandItem();
        ItemStack off = mc.player.getOffhandItem();
        return main.is(Items.ENDER_CHEST) || off.is(Items.ENDER_CHEST);
    }
}
