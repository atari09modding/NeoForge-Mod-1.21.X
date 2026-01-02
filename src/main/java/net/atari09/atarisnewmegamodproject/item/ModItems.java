package net.atari09.atarisnewmegamodproject.item;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.custom.ModArmorItem;
import net.atari09.atarisnewmegamodproject.item.custom.ChiselItem;
import net.atari09.atarisnewmegamodproject.item.custom.FuelItem;
import net.atari09.atarisnewmegamodproject.item.custom.HammerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static  final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AtariMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            ()-> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.atarisnewmegamodproject.radish.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice",
            () -> new FuelItem(new Item.Properties(), 800));
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> BISMUTH_SWORD = ITEMS.register("bismuth_sword",
            ()->new SwordItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BISMUTH, 5f,7f))));
    public static final DeferredItem<PickaxeItem> BISMUTH_PICKAXE = ITEMS.register("bismuth_pickaxe",
            ()->new PickaxeItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.BISMUTH, 1f,-2.8f))));
    public static final DeferredItem<ShovelItem> BISMUTH_SHOVEL = ITEMS.register("bismuth_shovel",
            ()->new ShovelItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.BISMUTH, 5f,-2.0f))));
    public static final DeferredItem<AxeItem> BISMUTH_AXE = ITEMS.register("bismuth_axe",
            ()->new AxeItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.BISMUTH, 12f,-4f))));
    public static final DeferredItem<HoeItem> BISMUTH_HOE = ITEMS.register("bismuth_hoe",
            ()->new HoeItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.BISMUTH, -10f,-3f))));
    public static final DeferredItem<HammerItem> BISMUTH_HAMMER = ITEMS.register("bismuth_hammer",
            ()->new HammerItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.BISMUTH, 7f,-3.5f))));

    public static final DeferredItem<ArmorItem> BISMUTH_HELMET = ITEMS.register("bismuth_helmet",
            () -> new ModArmorItem(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(50))));
    public static final DeferredItem<ArmorItem> BISMUTH_CHESTPLATE = ITEMS.register("bismuth_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(50))));
    public static final DeferredItem<ArmorItem> BISMUTH_LEGGINGS = ITEMS.register("bismuth_leggings",
            () -> new ArmorItem(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(50))));
    public static final DeferredItem<ArmorItem> BISMUTH_BOOTS = ITEMS.register("bismuth_boots",
            () -> new ArmorItem(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(50))));

    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR = ITEMS.register("bismuth_horse_armor",
            ()-> new AnimalArmorItem(ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> KAUPEN_SMITHING_TEMPLATE = ITEMS.register("kaupen_armor_trim_smithing_template",
            ()->SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(AtariMod.MOD_ID, "kaupen")));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
