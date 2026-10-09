package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;

/**
 * XCarry: quickly moves items from an open container into the player's inventory.
 */
public class XCarry extends Module {

    public XCarry() {
        super("XCarry", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.screen == null) {
            return;
        }
        if (mc.screen instanceof AbstractContainerScreen<?> screen) {
            for (int i = 0; i < screen.getMenu().slots.size(); i++) {
                if (!screen.getMenu().slots.get(i).isEmpty()) {
                    screen.getMenu().quickMoveStack(player, i);
                }
            }
        }
    }
}
