package net.atari09.atarisnewmegamodproject.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ATARI = "key.category.atarisnewmegamodproject.atari";
    public static final String KEY_DRINK_WATER = "key.atarisnewmegamodproject.drink_water";
    public static final String KEY_JETPACKCHESTPLATE_BOOST = "key.atarisnewmegamodproject.jetpackchestplate_boost";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_ATARI);

    public static final KeyMapping JETPACKCHESTPLATE_BOOST_KEY = new KeyMapping(KEY_JETPACKCHESTPLATE_BOOST, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORY_ATARI);
}
