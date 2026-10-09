package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.meteorneo.core.Modules;
import net.meteorneo.core.ZhNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClickGUI: a compact dropdown-style module panel. A row of category buttons
 * at the top; clicking a category reveals that category's module list below.
 * Open with the configured key (right Shift) or with .ClickGUI.
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

        private Category selected = Category.COMBAT;

        public ClickGuiScreen() {
            super(Component.literal("MeteorNeoForge ClickGUI"));
        }

        @Override
        protected void init() {
            buildUi();
        }

        private void select(Category cat) {
            selected = cat;
            buildUi();
        }

        private void buildUi() {
            clearWidgets();
            int bw = 92, bh = 16, gap = 2;
            int x = 6, y = 6;
            for (Category c : Category.values()) {
                addRenderableWidget(new CatButton(c, this, x, y, bw, bh));
                x += bw + gap;
            }
            int mx = 6, my = 30, mbw = 92, mbh = 13, mgap = 1;
            for (Module m : modulesOf(selected)) {
                addRenderableWidget(new ToggleButton(m, mx, my, mbw, mbh));
                my += mbh + mgap;
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

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    /** A small category tab at the top; the selected one is highlighted. */
    private static class CatButton extends Button {

        private final Category cat;
        private final ClickGuiScreen screen;

        CatButton(Category cat, ClickGuiScreen screen, int x, int y, int w, int h) {
            super(x, y, w, h, Component.literal(ZhNames.cat(cat.getName())),
                    b -> screen.select(cat), DEFAULT_NARRATION);
            this.cat = cat;
            this.screen = screen;
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mx, int my, float pt) {
            boolean sel = screen.selected == cat;
            int bg = sel ? 0xFF1565C0 : (isHovered() ? 0xFF455A64 : 0xFF37474F);
            g.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
            g.fill(getX(), getY(), getX() + getWidth(), getY() + 1, 0xFF90A4AE);
            g.drawCenteredString(Minecraft.getInstance().font,
                    Component.literal(ZhNames.cat(cat.getName())),
                    getX() + getWidth() / 2,
                    getY() + (getHeight() - 8) / 2,
                    0xFFFFFFFF);
        }
    }

    /** A compact module toggle button reflecting the enabled state. */
    private static class ToggleButton extends Button {

        private final Module module;

        ToggleButton(Module m, int x, int y, int w, int h) {
            super(x, y, w, h, Component.literal(ZhNames.mod(m.getName())),
                    b -> m.toggle(), DEFAULT_NARRATION);
            this.module = m;
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mx, int my, float pt) {
            boolean on = module.isEnabled();
            int bg = on ? (isHovered() ? 0xFF2E7D32 : 0xFF1B5E20)
                        : (isHovered() ? 0xFF546E7A : 0xFF37474F);
            g.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
            g.fill(getX(), getY(), getX() + getWidth(), getY() + 1, on ? 0xFF66BB6A : 0xFF90A4AE);
            g.drawCenteredString(Minecraft.getInstance().font,
                    Component.literal(ZhNames.mod(module.getName())),
                    getX() + getWidth() / 2,
                    getY() + (getHeight() - 7) / 2,
                    on ? 0xFFE8F5E9 : 0xFFECEFF1);
        }
    }
}
