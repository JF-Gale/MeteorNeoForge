package net.meteorneo;

import net.meteorneo.core.Modules;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.client.event.ClientChatEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@Mod(MeteorNeoForge.MOD_ID)
public class MeteorNeoForge {

    public static final String MOD_ID = "meteor_neoforge";

    public MeteorNeoForge(IEventBus modBus) {
        // Register the module registry (framework bootstrap).
        Modules.init();
        // Listen to client game events on the game bus.
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }
        Modules.tick(mc);
    }

    @SubscribeEvent
    public void onRenderWorld(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        float partialTick = event.getPartialTick().getGameTimeDeltaTicks();
        Modules.renderWorld(mc, event.getPoseStack(), partialTick);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String msg = event.getMessage();
        if (msg == null || !msg.startsWith(".")) {
            return;
        }
        String cmd = msg.substring(1).trim();
        String[] parts = cmd.split(" ", 2);
        String name = parts[0];
        for (Module m : Modules.getAll()) {
            if (m.getName().equalsIgnoreCase(name)) {
                m.toggle();
                event.setCanceled(true);
                return;
            }
        }
    }
}
