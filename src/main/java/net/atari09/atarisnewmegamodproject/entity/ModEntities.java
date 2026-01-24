package net.atari09.atarisnewmegamodproject.entity;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.entity.custom.ChairEntity;
import net.atari09.atarisnewmegamodproject.entity.custom.GeckoEntity;
import net.atari09.atarisnewmegamodproject.entity.custom.MagicProjectileEntity;
import net.atari09.atarisnewmegamodproject.entity.custom.TomahawkProjectileEntity;
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

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }


}
