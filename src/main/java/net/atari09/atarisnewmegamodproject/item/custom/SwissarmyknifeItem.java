package net.atari09.atarisnewmegamodproject.item.custom;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.item.client.LogMineBlockItemRenderer;
import net.atari09.atarisnewmegamodproject.item.client.SwissArmyKnifeRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SwissarmyknifeItem extends DiggerItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int currtool;
    private int switchcooldown;

    public SwissarmyknifeItem(Properties properties) {
        super(Tiers.DIAMOND,BlockTags.AIR, properties.component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.AIR), 15.0F)), 1.0F, 1)));
        this.currtool = 0;
        this.switchcooldown = 0;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
    }

    private PlayState predicate(AnimationState<SwissarmyknifeItem> state) {
        return state.setAndContinue(RawAnimation.begin().thenLoop(Map.of(
                0,"animation_none",
                1,"animation_sword",
                2,"animation_axe",
                3,"animation_pickaxe",
                4,"animation_shovel").get(currtool)));
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private SwissArmyKnifeRenderer renderer;
            @Override
            public @Nullable BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new SwissArmyKnifeRenderer();
                }
                return this.renderer;

            }
        });
    }


    public static ItemAttributeModifiers modifyAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)(attackDamage + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    private void setTool(ItemStack stack, int i){
        currtool = i;
        Map<Integer, Tool> toolMap = Map.of(
                0,this.getTier().createToolProperties(BlockTags.AIR),
                1,new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2),
                2,this.getTier().createToolProperties(BlockTags.MINEABLE_WITH_AXE),
                3,this.getTier().createToolProperties(BlockTags.MINEABLE_WITH_PICKAXE),
                4,this.getTier().createToolProperties(BlockTags.MINEABLE_WITH_SHOVEL)
        );
        Map<Integer, ItemAttributeModifiers> attributeMap = Map.of(
                0, createAttributes(Tiers.WOOD,0.5F,-1.5F),
                1, createAttributes(Tiers.DIAMOND, 3, -2.4F),
                2, createAttributes(Tiers.DIAMOND, 5.0F, -3.0F),
                3, createAttributes(Tiers.DIAMOND, 1.0F, -2.8F),
                4, createAttributes(Tiers.DIAMOND, 1.5F, -3.0F)
        );

        stack.set(DataComponents.TOOL, toolMap.get(i));
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, attributeMap.get(i));

    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)(attackDamage + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Tool tool = (Tool)stack.get(DataComponents.TOOL);
        return tool != null ? tool.getMiningSpeed(state) : 1.0F;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentTick();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if(entity instanceof Player player){
            if(Screen.hasAltDown() && player.getMainHandItem().is(ModItems.SWISSARMYKNIFE.get()) && switchcooldown <=0){
                if (currtool+1 >4){
                    currtool = -1;
                }
                    setTool(stack, currtool+1);
                    switchcooldown = 20;
            }
        }
        setTool(stack, currtool);
        switchcooldown--;
    }
}
