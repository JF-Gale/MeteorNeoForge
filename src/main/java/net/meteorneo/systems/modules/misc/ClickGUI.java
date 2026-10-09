package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Label;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClickGUI: a grouped, color-coded module panel. Modules are laid out in
 * category cards across a grid; green = enabled, dark = disabled, click to toggle.
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
            int margin = 8;
            int pad = 6;
            int top = 30;
            int cols = 3;
            int rows = 2;
            int cw = (width - margin * 2 - pad * (cols - 1)) / cols;
            int ch = (height - top - margin - pad * (rows - 1)) / rows;

            for (Category cat : Category.values()) {
                int idx = cat.ordinal();
                int r = idx / cols;
                int c = idx % cols;
                int cx = margin + c * (cw + pad);
                int cy = top + r * (ch + pad);

                addRenderableWidget(Label.builder(Component.literal(cat.getName() + " (" + countOf(cat) + ")"),
                        Minecraft.getInstance().font)
                        .pos(cx + 4, cy)
                        .color(0xFFB0BEC5)
                        .build());

                List<Module> list = modulesOf(cat);
                int count = list.size();
                if (count == 0) {
                    continue;
                }
                int bh = (ch - 18 - 8) / count;
                if (bh > 22) {
                    bh = 22;
                }
                if (bh < 14) {
                    bh = 14;
                }
                int bx = cx + 4;
                int bw = cw - 8;
                int by = cy + 18;
                for (Module m : list) {
                    addRenderableWidget(new ToggleButton(m, bx, by, bw, bh));
                    by += bh + 1;
                }
            }
        }

        private List<Module> modulesOf(Category cat) {
            List<Module> out = new ArrayList<>();
            for (Module m : Modules.getAll()) {
                if (m.getCategory() == cat) {
                    out.add(m);
                }
            }
            return out;
        }

        private int countOf(Category cat) {
            return modulesOf(cat).size();
        }

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    /** A colored toggle button that reflects the module's enabled state. */
    private static class ToggleButton extends Button {

        private final Module module;

        ToggleButton(Module module, int x, int y, int w, int h) {
            super(x, y, w, h, Component.literal(module.getName()),
                    b -> module.toggle(), DEFAULT_NARRATION);
            this.module = module;
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
            boolean on = module.isEnabled();
            int bg;
            if (on) {
                bg = isHovered() ? 0xFF2E7D32 : 0xFF1B5E20;
            } else {
                bg = isHovered() ? 0xFF546E7A : 0xFF37474F;
            }
            g.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
            g.fill(getX(), getY(), getX() + getWidth(), getY() + 1, on ? 0xFF66BB6A : 0xFF90A4AE);
            int txtCol = on ? 0xFFE8F5E9 : 0xFFECEFF1;
            g.drawCenteredString(Minecraft.getInstance().font,
                    Component.literal(module.getName()),
                    getX() + getWidth() / 2,
                    getY() + (getHeight() - 8) / 2,
                    txtCol);
        }
    }
}
