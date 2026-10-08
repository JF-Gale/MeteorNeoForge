package net.meteorneo.core;

import net.meteorneo.systems.modules.combat.AutoArmor;
import net.meteorneo.systems.modules.combat.AutoSword;
import net.meteorneo.systems.modules.combat.AutoTotem;
import net.meteorneo.systems.modules.combat.Criticals;
import net.meteorneo.systems.modules.combat.KillAura;
import net.meteorneo.systems.modules.movement.AirJump;
import net.meteorneo.systems.modules.movement.AutoJump;
import net.meteorneo.systems.modules.movement.AutoWalk;
import net.meteorneo.systems.modules.movement.Flight;
import net.meteorneo.systems.modules.movement.NoFall;
import net.meteorneo.systems.modules.movement.Speed;
import net.meteorneo.systems.modules.movement.Sprint;
import net.meteorneo.systems.modules.render.Fullbright;
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
        register(new AutoArmor());
        register(new AutoJump());
        register(new AutoSword());
        register(new AutoTotem());
        register(new AutoWalk());
        register(new Criticals());
        register(new Flight());
        register(new Fullbright());
        register(new KillAura());
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
