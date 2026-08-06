package net.atari09.atarisnewmegamodproject.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.CuriosTriggers;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class CuriosTestProvider extends CuriosDataProvider {

    public CuriosTestProvider(String modId, PackOutput output,
                              ExistingFileHelper fileHelper,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createSlot("newslot1").dropRule(ICurio.DropRule.DEFAULT).addCosmetic(true);

        this.createEntities("new_entitities").addPlayer().addSlots("head","ring","belt");


    }
}