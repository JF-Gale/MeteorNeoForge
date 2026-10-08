package net.meteorneo.systems.settings;

import java.util.function.Consumer;

/** A boolean (on/off) module setting. */
public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, String description, boolean defaultValue) {
        this(name, description, defaultValue, null);
    }

    public BooleanSetting(String name, String description, boolean defaultValue, Consumer<Boolean> onChanged) {
        super(name, description, defaultValue, onChanged);
    }
}
