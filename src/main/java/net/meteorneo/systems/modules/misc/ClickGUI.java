package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * ClickGUI: opens a simple module-list screen where each module can be toggled by clicking.
 */
public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Category.MISC);
    }

    @Override
    protected void onEnable() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.setScreen(new ClickGuiScreen());
        }
    }

    public static class ClickGuiScreen extends Screen {

        public ClickGuiScreen() {
            super(Component.literal("MeteorNeoForge ClickGUI"));
        }

        @Override
        protected void init() {
            int y = 20;
            for (Module m : Modules.getAll()) {
                addRenderableWidget(Button.builder(
                        Component.literal(m.getName()),
                        b -> m.toggle())
                        .bounds(10, y, 170, 20)
                        .build());
                y += 22;
            }
        }

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }
}
