package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * EnderChest: opens the ender chest screen by simulating a right-click
 * on the block below when an ender chest is held in hand.
 */
public class EnderChest extends Module {

    private boolean prevHeld = false;

    public EnderChest() {
        super("EnderChest", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        if (mc.player == null) {
            return;
        }
        boolean held = isHoldingEnderChest(mc);
        if (held && !prevHeld && mc.screen == null) {
            BlockPos p = mc.player.blockPosition();
            Vec3 hit = new Vec3(p.getX() + 0.5, p.getY() - 1, p.getZ() + 0.5);
            BlockHitResult hr = new BlockHitResult(hit, Direction.UP, p.below(), false);
            mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, hr);
        }
        prevHeld = held;
    }

    private boolean isHoldingEnderChest(Minecraft mc) {
        ItemStack main = mc.player.getMainHandItem();
        ItemStack off = mc.player.getOffhandItem();
        return main.is(Items.ENDER_CHEST) || off.is(Items.ENDER_CHEST);
    }
}
