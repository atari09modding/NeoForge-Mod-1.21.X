package net.atari09.atarisnewmegamodproject.util;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BISMUTH_TOOL = createTag("needs_bismuth_tool");
        public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = createTag("incorrect_for_bismuth_tool");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, name));
        }
    }
    public static class Entitys {
        public static final TagKey<EntityType<?>> BROK_FRIENDS = createTag("brok_friends");
        public static final TagKey<EntityType<?>> PIRANHA_ATTACKABLE = createTag("piranha_attackable");

        private static TagKey<EntityType<?>> createTag(String name){
            return TagKey.create(Registries.ENTITY_TYPE, AtariMod.res(name));
        }
    }


    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, name));
        }
    }

}
