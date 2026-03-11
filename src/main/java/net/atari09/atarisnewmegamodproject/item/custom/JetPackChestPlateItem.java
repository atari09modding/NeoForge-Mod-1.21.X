package net.atari09.atarisnewmegamodproject.item.custom;

import net.atari09.atarisnewmegamodproject.item.client.JetPackChestPlateRenderer;
import net.atari09.atarisnewmegamodproject.item.client.SwissArmyKnifeRenderer;
import net.atari09.atarisnewmegamodproject.util.KeyBinding;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;

import java.util.List;
import java.util.function.Consumer;

public class JetPackChestPlateItem extends ElytraItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final JetPackChestPlateItem animatable = this;
    public JetPackChestPlateItem(Properties properties) {
        super(properties);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"predicate",this::predicate));
    }

    private PlayState predicate(AnimationState<JetPackChestPlateItem> state) {
        Entity entity = state.getData(DataTickets.ENTITY);

        if(entity != null && isFlying(entity)){
            return state.setAndContinue(RawAnimation.begin().thenPlay("turn_on").thenPlay("on").thenLoop("on"));
        } else {
            return state.setAndContinue(RawAnimation.begin().thenPlay("turn_off").thenPlay("off").thenLoop("off"));
        }
    }

    private boolean isFlying(Entity entity) {
        return ((LivingEntity) entity).isFallFlying();
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private JetPackChestPlateRenderer renderer;
            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null) {
                    this.renderer = new JetPackChestPlateRenderer(animatable);
                }
                return this.renderer;

            }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.literal("Press §4" + KeyBinding.JETPACKCHESTPLATE_BOOST_KEY.getKey().getName() + "§r to boost."));
        } else  {
            tooltipComponents.add(Component.literal("Press §3Shift§r for more information."));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
