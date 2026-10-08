package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

/**
 * SpawnProofer: warns when the player is standing in an area where monsters can spawn.
 */
public class SpawnProofer extends Module {

    public SpawnProofer() {
        super("SpawnProofer", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos pos = player.blockPosition();
        int light = mc.level.getMaxLocalRawBrightness(pos);
        if (light < 7) {
            player.displayClientMessage(Component.literal("Monsters can spawn here!"), true);
        }
    }
}
