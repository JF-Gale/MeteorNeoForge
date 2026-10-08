package net.meteorneo.systems.settings;

import java.util.function.Consumer;

/**
 * A single configurable value on a module, modeled after Meteor Client's Setting.
 */
public abstract class Setting<T> {

    public final String name;
    public final String description;
    private final Consumer<T> onChanged;
    private T value;

    protected Setting(String name, String description, T defaultValue, Consumer<T> onChanged) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
        this.onChanged = onChanged;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        this.value = newValue;
        if (onChanged != null) {
            onChanged.accept(newValue);
        }
    }
}
