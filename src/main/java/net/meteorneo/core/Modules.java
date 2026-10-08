package net.meteorneo.core;

import net.meteorneo.systems.modules.combat.AimAssist;
import net.meteorneo.systems.modules.combat.AntiAim;
import net.meteorneo.systems.modules.combat.AntiAnvil;
import net.meteorneo.systems.modules.combat.AntiBed;
import net.meteorneo.systems.modules.combat.AutoArmor;
import net.meteorneo.systems.modules.combat.AutoCity;
import net.meteorneo.systems.modules.combat.AutoSword;
import net.meteorneo.systems.modules.combat.AutoTotem;
import net.meteorneo.systems.modules.combat.AutoWeb;
import net.meteorneo.systems.modules.combat.BowAim;
import net.meteorneo.systems.modules.combat.Criticals;
import net.meteorneo.systems.modules.combat.HoleFiller;
import net.meteorneo.systems.modules.combat.KillAura;
import net.meteorneo.systems.modules.misc.Announcer;
import net.meteorneo.systems.modules.misc.AutoFish;
import net.meteorneo.systems.modules.misc.AutoLog;
import net.meteorneo.systems.modules.misc.Notifications;
import net.meteorneo.systems.modules.misc.Spammer;
import net.meteorneo.systems.modules.movement.AirJump;
import net.meteorneo.systems.modules.movement.AntiVoid;
import net.meteorneo.systems.modules.movement.AutoJump;
import net.meteorneo.systems.modules.movement.AutoWalk;
import net.meteorneo.systems.modules.movement.BunnyHop;
import net.meteorneo.systems.modules.movement.ElytraFly;
import net.meteorneo.systems.modules.movement.Flight;
import net.meteorneo.systems.modules.movement.LongJump;
import net.meteorneo.systems.modules.movement.NoFall;
import net.meteorneo.systems.modules.movement.NoSlow;
import net.meteorneo.systems.modules.movement.Parkour;
import net.meteorneo.systems.modules.movement.SafeWalk;
import net.meteorneo.systems.modules.movement.Scaffold;
import net.meteorneo.systems.modules.movement.Spider;
import net.meteorneo.systems.modules.movement.Speed;
import net.meteorneo.systems.modules.movement.Sprint;
import net.meteorneo.systems.modules.movement.Step;
import net.meteorneo.systems.modules.movement.Strafe;
import net.meteorneo.systems.modules.player.AntiAFK;
import net.meteorneo.systems.modules.player.AntiLevitation;
import net.meteorneo.systems.modules.player.AutoEat;
import net.meteorneo.systems.modules.player.AutoRespawn;
import net.meteorneo.systems.modules.player.AutoTool;
import net.meteorneo.systems.modules.player.ChestStealer;
import net.meteorneo.systems.modules.player.FastUse;
import net.meteorneo.systems.modules.player.GhostHand;
import net.meteorneo.systems.modules.player.Sneak;
import net.meteorneo.systems.modules.render.Fullbright;
import net.meteorneo.systems.modules.world.AirPlace;
import net.meteorneo.systems.modules.world.AntiCactus;
import net.meteorneo.systems.modules.world.AutoBreed;
import net.meteorneo.systems.modules.world.AutoFarm;
import net.meteorneo.systems.modules.world.AutoMine;
import net.meteorneo.systems.modules.world.AutoTrap;
import net.meteorneo.systems.modules.world.BaseFinder;
import net.meteorneo.systems.modules.world.SpawnProofer;
import net.meteorneo.systems.modules.world.StashFinder;
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
        register(new AimAssist());
        register(new AirJump());
        register(new AirPlace());
        register(new AntiAFK());
        register(new AntiAim());
        register(new AntiAnvil());
        register(new AntiBed());
        register(new AntiCactus());
        register(new AntiLevitation());
        register(new AntiVoid());
        register(new Announcer());
        register(new AutoArmor());
        register(new AutoBreed());
        register(new AutoCity());
        register(new AutoEat());
        register(new AutoFarm());
        register(new AutoFish());
        register(new AutoJump());
        register(new AutoLog());
        register(new AutoMine());
        register(new AutoTrap());
        register(new AutoRespawn());
        register(new BaseFinder());
        register(new StashFinder());
        register(new SpawnProofer());
        register(new AutoSword());
        register(new AutoTool());
        register(new AutoTotem());
        register(new AutoWalk());
        register(new AutoWeb());
        register(new BowAim());
        register(new BunnyHop());
        register(new Criticals());
        register(new ElytraFly());
        register(new FastUse());
        register(new Flight());
        register(new Fullbright());
        register(new HoleFiller());
        register(new KillAura());
        register(new ChestStealer());
        register(new Sneak());
        register(new GhostHand());
        register(new LongJump());
        register(new NoFall());
        register(new NoSlow());
        register(new Notifications());
        register(new Parkour());
        register(new SafeWalk());
        register(new Scaffold());
        register(new Spider());
        register(new Speed());
        register(new Sprint());
        register(new Step());
        register(new Strafe());
        register(new Spammer());
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
