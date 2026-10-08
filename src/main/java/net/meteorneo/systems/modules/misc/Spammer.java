package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

/**
 * Spammer: periodically sends a rotating set of chat messages.
 */
public class Spammer extends Module {

    private final String[] messages = { "Hello!", "GG", "Nice!" };
    private int ticks = 0;

    public Spammer() {
        super("Spammer", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (++ticks % 100 == 0) {
            String msg = messages[ticks / 100 % messages.length];
            player.displayClientMessage(Component.literal(msg), false);
        }
    }
}
