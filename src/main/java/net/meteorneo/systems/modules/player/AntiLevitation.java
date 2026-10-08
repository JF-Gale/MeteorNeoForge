package net.meteorneo.systems.modules.player;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;

/**
 * AntiLevitation: jumps to reduce floating when affected by levitation.
 */
public class AntiLevitation extends Module {

    public AntiLevitation() {
        super("AntiLevitation", Category.PLAYER);
    }

    @Override
    public void onTick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        if (player.hasEffect(MobEffects.LEVITATION)) {
            player.jumpFromGround();
        }
    }
}
