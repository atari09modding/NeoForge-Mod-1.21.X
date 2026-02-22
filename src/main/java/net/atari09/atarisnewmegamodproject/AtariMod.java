package net.atari09.atarisnewmegamodproject;

import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.atari09.atarisnewmegamodproject.block.client.LogMineBlockRenderer;
import net.atari09.atarisnewmegamodproject.block.entity.ModBlockEntities;
import net.atari09.atarisnewmegamodproject.block.entity.renderer.PedestalBlockEntityRenderer;
import net.atari09.atarisnewmegamodproject.component.ModDataComponents;
import net.atari09.atarisnewmegamodproject.effect.ModEffects;
import net.atari09.atarisnewmegamodproject.enchantment.ModEnchantmentEffects;
import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.entity.client.*;
import net.atari09.atarisnewmegamodproject.item.ModCreativeModeTabs;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.loot.ModLootModifiers;
import net.atari09.atarisnewmegamodproject.particle.BismuthParticles;
import net.atari09.atarisnewmegamodproject.particle.ModParticles;
import net.atari09.atarisnewmegamodproject.potion.ModPotions;
import net.atari09.atarisnewmegamodproject.recipe.ModRecipes;
import net.atari09.atarisnewmegamodproject.screen.ModMenuTypes;
import net.atari09.atarisnewmegamodproject.screen.custom.GrowthChamberScreen;
import net.atari09.atarisnewmegamodproject.screen.custom.PedestalScreen;
import net.atari09.atarisnewmegamodproject.sound.ModSounds;
import net.atari09.atarisnewmegamodproject.util.ModItemProperties;
import net.atari09.atarisnewmegamodproject.villager.ModVillagers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import software.bernie.geckolib.GeckoLib;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@net.neoforged.fml.common.Mod(AtariMod.MOD_ID)
public class AtariMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "atarisnewmegamodproject";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation res(String path){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AtariMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);



        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


        ModCreativeModeTabs.register(modEventBus);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModDataComponents.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        ModVillagers.register(modEventBus);
        ModParticles.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);


    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.BISMUTH_BLOCK);
            event.accept(ModBlocks.BISMUTH_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
            EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
            EntityRenderers.register(ModEntities.CHAIR_ENTITY.get(), ChairRenderer::new);
            EntityRenderers.register(ModEntities.MAGIC_PROJECTILE.get(), MagicProjectileRenderer::new);
            EntityRenderers.register(ModEntities.BROK.get(), BrokRenderer::new);
            EntityRenderers.register(ModEntities.BROK_STONE.get(), BrokStoneRenderer::new);
            EntityRenderers.register(ModEntities.PIRANHA.get(), PiranhaRenderer::new);

        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event){
            event.registerSpriteSet(ModParticles.BISMUTH_PARTICLES.get(), BismuthParticles.Provider::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.LOGMINE_BE.get(), LogMineBlockRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event){
            event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
            event.register(ModMenuTypes.GROWTH_CHAMBER_MENU.get(), GrowthChamberScreen::new);
        }
    }
}
