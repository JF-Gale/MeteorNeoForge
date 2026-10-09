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
        private int scrollOffset;
        private final List<Button> moduleButtons = new ArrayList<>();

        private static final int LIST_X = 6;
        private static final int LIST_Y = 30;
        private static final int ROW_H = 14;
        private static final int MBW = 92;
        private static final int MBH = 13;

        public ClickGuiScreen() {
            super(Component.literal("MeteorNeoForge ClickGUI"));
        }

        @Override
        protected void init() {
            buildUi();
        }

        private void select(Category cat) {
            selected = cat;
            scrollOffset = 0;
            buildUi();
        }

        private void buildUi() {
            clearWidgets();
            moduleButtons.clear();
            int bw = 92, bh = 16, gap = 2;
            int x = 6, y = 6;
            for (Category c : Category.values()) {
                addRenderableWidget(new CatButton(c, this, x, y, bw, bh));
                x += bw + gap;
            }
            rebuildModuleButtons();
        }

        private void rebuildModuleButtons() {
            for (Button b : moduleButtons) {
                removeWidget(b);
            }
            moduleButtons.clear();
            List<Module> list = modulesOf(selected);
            int listH = height - LIST_Y - 10;
            int maxScroll = Math.max(0, list.size() * ROW_H - listH);
            scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
            int visible = Math.max(0, listH / ROW_H);
            for (int i = 0; i < list.size(); i++) {
                int by = LIST_Y + i * ROW_H - scrollOffset;
                if (by < LIST_Y - MBH || by > LIST_Y + listH) {
                    continue; // outside the visible list area
                }
                ToggleButton tb = new ToggleButton(list.get(i), LIST_X, by, MBW, MBH);
                moduleButtons.add(tb);
                addRenderableWidget(tb);
            }
        }

        @Override
        public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
            int listH = height - LIST_Y - 10;
            List<Module> list = modulesOf(selected);
            int maxScroll = Math.max(0, list.size() * ROW_H - listH);
            if (maxScroll == 0) {
                return false;
            }
            int before = scrollOffset;
            scrollOffset = Math.max(0, Math.min(maxScroll, scrollOffset + (int) (scrollY * ROW_H)));
            if (scrollOffset != before) {
                rebuildModuleButtons();
            }
            return true;
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
