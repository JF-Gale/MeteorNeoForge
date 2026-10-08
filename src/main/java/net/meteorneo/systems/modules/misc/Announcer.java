package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

/**
 * Announcer: periodically sends a client message.
 */
public class Announcer extends Module {

    private int ticks = 0;

    public Announcer() {
        super("Announcer", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (++ticks % 400 == 0) {
            player.displayClientMessage(Component.literal("Hello from MeteorNeoForge"), false);
        }
    }
}
