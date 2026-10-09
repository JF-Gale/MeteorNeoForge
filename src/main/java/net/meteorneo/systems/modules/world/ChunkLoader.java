package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * ChunkLoader: keeps the player's chunk loaded by touching it every tick.
 */
public class ChunkLoader extends Module {

    public ChunkLoader() {
        super("ChunkLoader", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        mc.level.getChunk(player.chunkPosition().x, player.chunkPosition().z);
    }
}
