package net.meteorneo.core;

import net.minecraft.client.Minecraft;

/**
 * Base class for every module, modeled after the Meteor Client Module.
 */
public abstract class Module {

    private final String name;
    private final Category category;
    private boolean enabled;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    /** Called once when the module is switched on. */
    protected void onEnable() {
    }

    /** Called once when the module is switched off. */
    protected void onDisable() {
    }

    /** Called every client tick while the module is enabled. */
    public void onTick(Minecraft mc) {
    }
}
