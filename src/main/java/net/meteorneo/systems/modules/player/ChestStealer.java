package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ChestScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.ChestMenu;

/**
 * ChestStealer: automatically moves items from an open chest into the inventory.
 */
public class ChestStealer extends Module {

    public ChestStealer() {
        super("ChestStealer", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || !(mc.screen instanceof ChestScreen)) {
            return;
        }
        ChestMenu menu = ((ChestScreen) mc.screen).getMenu();
        int rows = menu.getContainerSize() - 36;
        for (int i = 0; i < rows; i++) {
            if (!menu.getItems().get(i).isEmpty()) {
                menu.quickMoveStack(player, i);
                return;
            }
        }
    }
}
