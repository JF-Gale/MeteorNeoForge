package net.meteorneo.systems.settings;

import java.util.function.Consumer;

/** An integer module setting with min/max bounds. */
public class IntSetting extends Setting<Integer> {

    private final int min;
    private final int max;

    public IntSetting(String name, String description, int defaultValue, int min, int max) {
        this(name, description, defaultValue, min, max, null);
    }

    public IntSetting(String name, String description, int defaultValue, int min, int max, Consumer<Integer> onChanged) {
        super(name, description, clamp(defaultValue, min, max), onChanged);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
