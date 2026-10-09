package net.meteorneo.systems.modules.misc;

import net.meteorneo.core.Category;
import net.meteorneo.core.Module;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Friend: maintains a list of friendly players that combat modules can skip.
 */
public class Friend extends Module {

    private final List<String> friends = new ArrayList<>();

    public Friend() {
        super("Friend", Category.MISC);
    }

    @Override
    public void onTick(Minecraft mc) {
        // Data-only module.
    }

    public boolean isFriend(String name) {
        return friends.contains(name);
    }

    public void add(String name) {
        if (!friends.contains(name)) {
            friends.add(name);
        }
    }

    public void remove(String name) {
        friends.remove(name);
    }

    public List<String> getFriends() {
        return Collections.unmodifiableList(friends);
    }
}
