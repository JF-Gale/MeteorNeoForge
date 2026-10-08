package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

/**
 * StashFinder: periodically scans the nearby area for valuable stashed blocks.
 */
public class StashFinder extends Module {

    private int timer = 0;

    public StashFinder() {
        super("StashFinder", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        if (++timer % 40 != 0) {
            return;
        }
        for (int x = -8; x <= 8; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -8; z <= 8; z++) {
                    BlockPos pos = player.blockPosition().offset(x, y, z);
                    BlockState state = mc.level.getBlockState(pos);
                    if (state.is(net.minecraft.world.level.block.Blocks.CHEST)
                            || state.is(net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK)
                            || state.is(net.minecraft.world.level.block.Blocks.NETHERITE_BLOCK)) {
                        player.displayClientMessage(Component.literal("Stash nearby!"), true);
                        return;
                    }
                }
            }
        }
    }
}
