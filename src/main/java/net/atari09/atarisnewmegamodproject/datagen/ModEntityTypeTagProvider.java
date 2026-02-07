package net.atari09.atarisnewmegamodproject.datagen;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {


    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider,AtariMod.MOD_ID,existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Entitys.BROK_FRIENDS)
                .add(ModEntities.BROK.get())
                .addTag(EntityTypeTags.WITHER_FRIENDS);

        tag(ModTags.Entitys.PIRANHA_ATTACKABLE)
                .add(EntityType.PLAYER)
                .addTag(EntityTypeTags.AQUATIC)
                .remove(EntityType.PUFFERFISH);

        tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
                .add(ModEntities.PIRANHA.get());
    }

}
