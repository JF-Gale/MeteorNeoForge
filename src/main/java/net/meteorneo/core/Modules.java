package net.meteorneo.core;

import net.meteorneo.systems.modules.movement.AirJump;
import net.meteorneo.systems.modules.movement.AutoJump;
import net.meteorneo.systems.modules.movement.Flight;
import net.meteorneo.systems.modules.movement.NoFall;
import net.meteorneo.systems.modules.movement.Speed;
import net.meteorneo.systems.modules.movement.Sprint;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central module registry. Modules are registered here in init().
 */
public final class Modules {

    private static final List<Module> MODULES = new ArrayList<>();

    private Modules() {
    }

    /** Bootstrap: register every module instance. */
    public static void init() {
        register(new AirJump());
        register(new AutoJump());
        register(new Flight());
        register(new NoFall());
        register(new Speed());
        register(new Sprint());
    }

    private static void register(Module module) {
        MODULES.add(module);
    }

    /** Advance every enabled module by one client tick. */
    public static void tick(Minecraft mc) {
        for (Module module : MODULES) {
            if (module.isEnabled()) {
                module.onTick(mc);
            }
        }
    }

    public static List<Module> getModules() {
        return Collections.unmodifiableList(MODULES);
    }

    public static <T extends Module> T get(Class<T> moduleClass) {
        for (Module module : MODULES) {
            if (moduleClass.isInstance(module)) {
                return moduleClass.cast(module);
            }
        }
        return null;
    }
}
