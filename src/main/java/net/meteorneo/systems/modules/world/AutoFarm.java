package net.meteorneo.systems.modules.world;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.Blocks;

/**
 * AutoFarm: holds the use key when standing on farmland to plant crops.
 */
public class AutoFarm extends Module {

    public AutoFarm() {
        super("AutoFarm", Category.WORLD);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        if (mc.level.getBlockState(player.blockPosition().below()).is(Blocks.FARMLAND)) {
            mc.options.keyUse.setDown(true);
        }
    }
}
