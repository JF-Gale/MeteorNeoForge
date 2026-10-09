package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

/**
 * Gamemode: switch the local player's game mode via .gamemode / .gm.
 * Usages: .gamemode c|creative|s|survival|a|adventure|sp|spectator
 * On servers the /gamemode command needs permission; in singleplayer with
 * cheats it always works.
 */
public class Gamemode extends Module {

    public Gamemode() {
        super("Gamemode", Category.PLAYER);
    }

    /** Execute the mode switch. Returns false if the argument was invalid. */
    public static boolean apply(Minecraft mc, String arg) {
        if (mc.player == null) {
            return false;
        }
        String mode;
        switch (arg.toLowerCase()) {
            case "c": case "creative": case "1": mode = "creative"; break;
            case "s": case "survival": case "0": mode = "survival"; break;
            case "a": case "adventure": case "2": mode = "adventure"; break;
            case "sp": case "spectator": case "3": mode = "spectator"; break;
            default: mode = null;
        }
        if (mode == null) {
            return false;
        }
        mc.player.connection.sendCommand("gamemode " + mode);
        return true;
    }
}
