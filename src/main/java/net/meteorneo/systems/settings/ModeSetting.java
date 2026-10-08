package net.meteorneo.systems.settings;

import java.util.Arrays;
import java.util.function.Consumer;

/** A setting that picks one of several string modes. */
public class ModeSetting extends Setting<String> {

    private final String[] modes;

    public ModeSetting(String name, String description, String defaultValue, String... modes) {
        this(name, description, defaultValue, null, modes);
    }

    public ModeSetting(String name, String description, String defaultValue, Consumer<String> onChanged, String... modes) {
        super(name, description, modes.length > 0 && Arrays.asList(modes).contains(defaultValue) ? defaultValue : (modes.length > 0 ? modes[0] : defaultValue), onChanged);
        this.modes = modes;
    }

    public String[] getModes() {
        return modes;
    }
}
