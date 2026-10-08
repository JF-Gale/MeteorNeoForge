package net.meteorneo.systems.settings;

import java.util.function.Consumer;

/** A floating point (double) module setting with min/max bounds. */
public class DoubleSetting extends Setting<Double> {

    private final double min;
    private final double max;

    public DoubleSetting(String name, String description, double defaultValue, double min, double max) {
        this(name, description, defaultValue, min, max, null);
    }

    public DoubleSetting(String name, String description, double defaultValue, double min, double max, Consumer<Double> onChanged) {
        super(name, description, clamp(defaultValue, min, max), onChanged);
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
