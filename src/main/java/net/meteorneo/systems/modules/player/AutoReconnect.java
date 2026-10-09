package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;

/**
 * AutoReconnect: when disconnected from a server, opens the server list so you can rejoin.
 */
public class AutoReconnect extends Module {

    private boolean handled;

    public AutoReconnect() {
        super("AutoReconnect", Category.PLAYER);
    }

    @Override
    protected void onEnable() {
        handled = false;
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.level == null && mc.player == null) {
            if (!handled) {
                handled = true;
                if (mc.getCurrentServer() != null) {
                    mc.setScreen(new JoinMultiplayerScreen(null));
                }
            }
        } else {
            handled = false;
        }
    }
}
