package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.BedBlock;

/**
 * AntiBed: warns when a bed is about to explode above the player's head.
 */
public class AntiBed extends Module {

    public AntiBed() {
        super("AntiBed", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos pos = player.blockPosition().above();
        if (mc.level.getBlockState(pos).getBlock() instanceof BedBlock) {
            player.displayClientMessage(Component.literal("Bed overhead!"), true);
        }
    }
}
