package net.atari09.mod.item;

import net.atari09.mod.AtariMod;
import net.atari09.mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AtariMod.MOD_ID);


    public static final Supplier<CreativeModeTab> BISMTUH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.atarisnewmegamodproject.bismuth_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> BISMTUH_BLOCKS_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID,"bismuth_items_tab"))
                    .title(Component.translatable("creativetab.atarisnewmegamodproject.bismuth_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                    })
                    .build());


    public static void register(IEventBus eventBus){
    CREATIVE_MODE_TAB.register(eventBus);
    }
}
