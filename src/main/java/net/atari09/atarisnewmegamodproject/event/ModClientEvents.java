package net.atari09.atarisnewmegamodproject.event;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.item.custom.JetPackChestPlateItem;
import net.atari09.atarisnewmegamodproject.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = AtariMod.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event){
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.KAUPEN_BOW.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 0.5f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinding.DRINKING_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event){
        if(KeyBinding.DRINKING_KEY.consumeClick()) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Drinking Water"));
        }
        if (KeyBinding.JETPACKCHESTPLATE_BOOST_KEY.consumeClick()){
            if(Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetPackChestPlateItem && Minecraft.getInstance().player.isFallFlying()) {
                Vec3 movement = Minecraft.getInstance().player.getDeltaMovement();
                double factor = 1 / movement.length();
                Minecraft.getInstance().player.setDeltaMovement(movement.add(movement.scale(factor)));
            }
        }
    }
}
