package net.atari09.atarisnewmegamodproject.entity;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AtariMod.MOD_ID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko",()-> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("gecko"));

    public static final Supplier<EntityType<TomahawkProjectileEntity>> TOMAHAWK =
            ENTITY_TYPES.register("tomahawk", () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC)//<TomahawkProjectileEntity> is somehow needed when Mobcategory is MISC
                    .sized(0.5f, 1.15f).build("tomahawk"));

    public static final Supplier<EntityType<ChairEntity>> CHAIR_ENTITY =
            ENTITY_TYPES.register("chair_entity", ()-> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("chair_entity"));

    public static final Supplier<EntityType<MagicProjectileEntity>> MAGIC_PROJECTILE =
            ENTITY_TYPES.register("magic_projectile", () -> EntityType.Builder.<MagicProjectileEntity>of(MagicProjectileEntity::new, MobCategory.MISC)//<TomahawkProjectileEntity> is somehow needed when Mobcategory is MISC
                    .sized(0.5f, 0.5f).build("magic_projectile"));

    public static final Supplier<EntityType<BrokStone>> BROK_STONE =
            ENTITY_TYPES.register("brok_stone_projectile", () -> EntityType.Builder.<BrokStone>of(BrokStone::new, MobCategory.MISC)//<TomahawkProjectileEntity> is somehow needed when Mobcategory is MISC
                    .sized(0.5f, 0.5f).build("brok_stone_projectile"));


    public static final Supplier<EntityType<BrokEntity>> BROK =
            ENTITY_TYPES.register("brok",()->EntityType.Builder.of(BrokEntity::new, MobCategory.MONSTER)
                    .sized(3f,1.5f).build("brok"));

    public static final Supplier<EntityType<PiranhaEntity>> PIRANHA =
            ENTITY_TYPES.register("piranha",()->EntityType.Builder.of(PiranhaEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.6f,0.4f).build("piranha"));

    public static final Supplier<EntityType<DragonflyEntity>> DRAGONFLY =
            ENTITY_TYPES.register("dragonfly",()->EntityType.Builder.of(DragonflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.4f,0.2f).build("dragonfly"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }


}
