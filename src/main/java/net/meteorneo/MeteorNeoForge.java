package net.meteorneo;

import net.meteorneo.core.Modules;
import net.meteorneo.core.Module;
import net.meteorneo.systems.modules.misc.ClickGUI;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.client.event.ClientChatEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.lwjgl.glfw.GLFW;

@Mod(MeteorNeoForge.MOD_ID)
public class MeteorNeoForge {

    public static final String MOD_ID = "meteor_neoforge";

    public static final KeyMapping OPEN_GUI = new KeyMapping(
            "key.meteor_neoforge.open_gui",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "Meteor NeoForge");

    public MeteorNeoForge(IEventBus modBus) {
        // Register the module registry (framework bootstrap).
        Modules.init();
        // Mod-bus events (key binding registration) must be registered on the mod bus only.
        modBus.addListener(this::onKeyMapping);
        // Client game events belong to the game bus. Register each individually so no
        // mod-bus handler is ever scanned by the game bus (and vice versa).
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
        NeoForge.EVENT_BUS.addListener(this::onRenderWorld);
        NeoForge.EVENT_BUS.addListener(this::onChat);
    }

    @SubscribeEvent
    public void onKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(OPEN_GUI);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        while (OPEN_GUI.consumeClick()) {
            ClickGUI gui = Modules.get(ClickGUI.class);
            if (gui != null && !gui.isEnabled()) {
                gui.setEnabled(true);
            }
        }
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
