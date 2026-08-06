package net.atari09.atarisnewmegamodproject.item;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.block.ModBlocks;
import net.atari09.atarisnewmegamodproject.component.ModDataComponents;
import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.item.custom.*;
import net.atari09.atarisnewmegamodproject.screen.custom.DrawableExplosionScreen;
import net.atari09.atarisnewmegamodproject.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

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

    public static final DeferredItem<Item> KAUPEN_BOW = ITEMS.register("kaupen_bow",
            () -> new BowItem(new Item.Properties().durability(5000)));

    public static final  DeferredItem<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1)));

    public static final DeferredItem<Item> RADISH_SEEDS = ITEMS.register("radish_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.RADISH_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> GOJI_BERRIES = ITEMS.register("goji_berries",
            () -> new ItemNameBlockItem(ModBlocks.GOJI_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.GOJI_BERRY)));

    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.register("gecko_spawn_egg",
            ()-> new DeferredSpawnEggItem(ModEntities.GECKO,0x31afaf,0xfac00,
                    new Item.Properties()));

    public static final DeferredItem<Item> TOMAHAWK = ITEMS.register("tomahawk",
            ()-> new TomahawkItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> RADIATION_STAFF = ITEMS.register("radiation_staff",
            ()-> new MagicWandItem(new Item.Properties().stacksTo(1).durability(1250)));

    public static final DeferredItem<AxeItem> REDSTONE_AXE = ITEMS.register("redstone_axe",
            ()->new AxeItem(ModToolTiers.REDSTONE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.REDSTONE, 12f,-2f))));

    public static final DeferredItem<Item> MAGMA_MACE = ITEMS.register("magma_mace",
            ()->new MaceItem(new Item.Properties().rarity(Rarity.EPIC).durability(500).component(DataComponents.TOOL, MaceItem.createToolProperties()).attributes(MaceItem.createAttributes()))
            {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    target.setRemainingFireTicks(100);
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<Item> MAGMA_CORE = ITEMS.register("magma_core",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> G4M = ITEMS.register("g4m_529_pro_guitar",
            ()-> new Item(new Item.Properties()){
            @Override
            public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
                ItemStack itemstack = player.getItemInHand(usedHand);
                if(usedHand == InteractionHand.MAIN_HAND){
                    level.playSound(player,player.getOnPos(),ModSounds.G4M.get(), SoundSource.PLAYERS);
                }
                return InteractionResultHolder.pass(itemstack);
            }
    });

    public static final DeferredItem<Item> BROK_SPAWN_EGG = ITEMS.register("brok_spawn_egg",
            ()-> new DeferredSpawnEggItem(ModEntities.BROK,0x626363,0x4c5c54,
                    new Item.Properties()));

    public static final DeferredItem<Item> PIRANHA_SPAWN_EGG = ITEMS.register("piranha_spawn_egg",
            ()-> new DeferredSpawnEggItem(ModEntities.PIRANHA,0x384a59,0x365596,
                    new Item.Properties()));

    public static final DeferredItem<Item> PIRANHA_BUCKET = ITEMS.register("piranha_bucket",
            ()->new MobBucketItem(ModEntities.PIRANHA.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));


    public static final DeferredItem<Item> LOGMINE_BLOCK_ITEM = ITEMS.register("logmine_block_item",
            ()->new LogMineBlockItem(ModBlocks.LOGMINE.get(), new Item.Properties()));

    public static final DeferredItem<SwissarmyknifeItem> SWISSARMYKNIFE = ITEMS.register("swissarmyknife",
            ()->new SwissarmyknifeItem(new Item.Properties().stacksTo(1).durability(2000)));

    public static final DeferredItem<Item> NUKEROD = ITEMS.register("nukerod",
            ()-> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public @NotNull InteractionResult useOn(UseOnContext context) {
                    BlockPos pos = context.getClickedPos();
                    Level level = context.getLevel();
                    if(!level.isClientSide) {
                        for(float momentum = 0.2f; momentum <= 2f; momentum += 0.1f){
                            for(float x = -0.1f;x <= momentum+0.1f; x += 0.1f){
                                float z = (float) Math.pow((Math.pow(momentum,2)-Math.pow(x,2)),0.5);
                                PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);
                                tnt.setPos(new Vec3(pos.getX(), pos.getY()+10, pos.getZ()));
                                tnt.setDeltaMovement(x,0,z);
                                level.addFreshEntity(tnt);

                                tnt = new PrimedTnt(EntityType.TNT, level);
                                tnt.setPos(new Vec3(pos.getX(), pos.getY()+10, pos.getZ()));
                                tnt.setDeltaMovement(x,0,-z);
                                level.addFreshEntity(tnt);

                                tnt = new PrimedTnt(EntityType.TNT, level);
                                tnt.setPos(new Vec3(pos.getX(), pos.getY()+10, pos.getZ()));
                                tnt.setDeltaMovement(-x,0,z);
                                level.addFreshEntity(tnt);

                                tnt = new PrimedTnt(EntityType.TNT, level);
                                tnt.setPos(new Vec3(pos.getX(), pos.getY()+10, pos.getZ()));
                                tnt.setDeltaMovement(-x,0,-z);
                                level.addFreshEntity(tnt);
                            }
                        }
                    }
                    return super.useOn(context);
                }
            });

    public static final DeferredItem<Item> JETPACKCHESTPLATE = ITEMS.register("jetpackchestplate",
            () -> new JetPackChestPlateItem(new Item.Properties().durability(3000).stacksTo(1)));

    public static final DeferredItem<Item> DRAGONFLY_SPAWN_EGG = ITEMS.register("dragonfly_spawn_egg",
            ()->new SpawnEggItem(ModEntities.DRAGONFLY.get(),0x1e7d6c,0x1be3d9, new Item.Properties()));

    public static final DeferredItem<SwordItem> MSPAINTSWORD = ITEMS.register("mspaintsword",
            ()->new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE,20f,7f))));

    public static final DeferredItem<Item> NUCLEARGRENADE = ITEMS.register("nucleargrenade",
            ()->new NuclearGrenadeItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> BAZOOKA = ITEMS.register("bazooka",
            () -> new Item(new Item.Properties().stacksTo(1).food(ModFoodProperties.RADISH).fireResistant()){
                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);
                    tnt.setPos(player.position().add(new Vec3(0,1,0)));
                    tnt.setDeltaMovement(player.getLookAngle());
                    level.addFreshEntity(tnt);

                    return super.use(level, player, usedHand);
                }
            });

    public static final DeferredItem<Item> TNTTORNADOROD = ITEMS.register("tnttornadorod",
            ()-> new Item(new Item.Properties().stacksTo(1)){


                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
                    if (usedHand.equals(InteractionHand.MAIN_HAND)) {
                        player.getMainHandItem().set(ModDataComponents.ROT, 1);
                    }


                    return super.use(level, player, usedHand);
                }

                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
                    if (level.isClientSide()) {
                        return;
                    }
                    if(entity instanceof Player player){
                        int i = 0;
                        if (player.getMainHandItem().has(ModDataComponents.ROT)) {
                            i = player.getMainHandItem().get(ModDataComponents.ROT);
                        }

                            if (i != 0) {
                                if (i < 360*5) {
                                    i+=5;
                                } else {
                                    i = 0;
                                }
                                player.getMainHandItem().set(ModDataComponents.ROT, i);
                                int strength = i / 360;
                                strength = (int) Math.ceil(((double) strength+1));

                                PrimedTnt tnt = new PrimedTnt(level,player.getX(),player.getY(),player.getZ(),player);
                                tnt.setPos(player.position());
                                Vec3 direction = new Vec3((double) strength /3,1,0);


                                direction = direction.yRot((float) Math.toRadians(i));


                                tnt.setDeltaMovement(direction);
                                level.addFreshEntity(tnt);
                            }

                            super.inventoryTick(stack, level, entity, slotId, isSelected);
                        }
                    }
            });

    public static final DeferredItem<Item> DRAWABLEEXPLOSIONROD = ITEMS.register("drawableexplosionrod",
            ()-> new Item(new Item.Properties().stacksTo(1)){


                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
                    if(level.isClientSide){
                        Minecraft.getInstance().setScreen(new DrawableExplosionScreen(Component.literal("Draw Shape")));
                    }

                    return super.use(level, player, usedHand);
                }

            });

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
