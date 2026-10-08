package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;

/**
 * BaseFinder: periodically scans the nearby area and warns when a chest is found.
 */
public class BaseFinder extends Module {

    private int timer = 0;

    public BaseFinder() {
        super("BaseFinder", Category.WORLD);
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
                    if (mc.level.getBlockState(pos).is(Blocks.CHEST)
                            || mc.level.getBlockState(pos).is(Blocks.ENDER_CHEST)) {
                        player.displayClientMessage(Component.literal("Base nearby!"), true);
                        return;
                    }
                }
            }
        }
    }
}
