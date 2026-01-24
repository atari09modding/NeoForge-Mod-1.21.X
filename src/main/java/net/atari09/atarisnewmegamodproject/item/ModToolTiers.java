package net.atari09.atarisnewmegamodproject.item;

import net.atari09.atarisnewmegamodproject.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier BISMUTH = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL,
            1400,4f,3f,280, ()-> Ingredient.of(ModItems.BISMUTH));

    public static final Tier REDSTONE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL,
            1467600,40f,0.5f,280, ()-> Ingredient.of(Items.REDSTONE));
}
