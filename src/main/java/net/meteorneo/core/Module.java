package net.meteorneo.core;

import net.meteorneo.systems.settings.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for every module, modeled after the Meteor Client Module.
 */
public abstract class Module {

    private final String name;
    private final Category category;
    private boolean enabled;
    private final List<Setting<?>> settings = new ArrayList<>();

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

    /** Register a setting on this module and return it for chaining. */
    protected <T extends Setting<?>> T setting(T setting) {
        settings.add(setting);
        return setting;
    }

    /** All settings declared by this module. */
    public List<Setting<?>> getSettings() {
        return Collections.unmodifiableList(settings);
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
