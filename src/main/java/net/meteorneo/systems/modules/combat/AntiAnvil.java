package net.meteorneo.systems.modules.combat;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;

/**
 * AntiAnvil: warns when an anvil is about to drop on the player's head.
 */
public class AntiAnvil extends Module {

    public AntiAnvil() {
        super("AntiAnvil", Category.COMBAT);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }
        BlockPos pos = player.blockPosition().above(2);
        if (mc.level.getBlockState(pos).is(Blocks.ANVIL)) {
            player.displayClientMessage(Component.literal("Anvil overhead!"), true);
        }
    }
}
