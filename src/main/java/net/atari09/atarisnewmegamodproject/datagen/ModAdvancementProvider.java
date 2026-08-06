package net.atari09.atarisnewmegamodproject.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosTriggers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider{




    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper, List<AdvancementGenerator> subProviders) {
        super(output, registries, existingFileHelper, subProviders);
    }

    public static class ModAdvancements implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

            HolderLookup.RegistryLookup<Biome> biomes = provider.lookupOrThrow(Registries.BIOME);

            Advancement.Builder.advancement()
                    .addCriterion("test",
                            CuriosTriggers.equipAtLocation( // for any biome just use equip probl
                                    ItemPredicate.Builder.item().of(Items.DIAMOND),
                                    LocationPredicate.Builder.location().setBiomes(
                                            HolderSet.direct(
                                                    biomes.getOrThrow(Biomes.PLAINS)
                                                    //theoretically add other biomes here
                                            )
                                    )
                            )
                    )
                    .save(saver, ResourceLocation.fromNamespaceAndPath("curiostest", "test"), existingFileHelper);
        }
    }


}
