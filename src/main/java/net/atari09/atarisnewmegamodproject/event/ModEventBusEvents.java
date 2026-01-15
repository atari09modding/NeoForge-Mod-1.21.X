package net.atari09.atarisnewmegamodproject.event;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.entity.client.GeckoModel;
import net.atari09.atarisnewmegamodproject.entity.custom.GeckoEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = AtariMod.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.GECKO.get(), GeckoEntity.createAttributtes().build());
    }
}
