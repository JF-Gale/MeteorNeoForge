package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * AutoMine: automatically starts mining the block the player is looking at.
 */
public class AutoMine extends Module {

    public AutoMine() {
        super("AutoMine", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null || mc.gameMode == null) {
            return;
        }
        HitResult hit = player.pick(4.5, 0.0f, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult bhr = (BlockHitResult) hit;
            mc.gameMode.startDestroyBlock(bhr.getBlockPos(), bhr.getDirection());
        }
    }
}
