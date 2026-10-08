package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

/**
 * AutoLog: disconnects when the player's health drops too low to avoid dying.
 */
public class AutoLog extends Module {

    public AutoLog() {
        super("AutoLog", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.getHealth() <= 6.0f) {
            if (mc.getConnection() != null) {
                mc.getConnection().disconnect(Component.literal("Disconnected by AutoLog"));
            }
            toggle();
        }
    }
}
