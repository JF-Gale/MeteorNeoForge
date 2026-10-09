package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * NoInteract: prevents interacting with container blocks like chests and furnaces.
 */
public class NoInteract extends Module {

    public NoInteract() {
        super("NoInteract", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null || mc.hitResult == null) {
            return;
        }
        if (mc.hitResult instanceof BlockHitResult bhr) {
            BlockState state = mc.level.getBlockState(bhr.getBlockPos());
            if (isInteractable(state)) {
                mc.options.keyUse.setDown(false);
            }
        }
    }

    private boolean isInteractable(BlockState state) {
        Block b = state.getBlock();
        return b instanceof ChestBlock
                || b instanceof FurnaceBlock
                || b instanceof CraftingTableBlock;
    }
}
