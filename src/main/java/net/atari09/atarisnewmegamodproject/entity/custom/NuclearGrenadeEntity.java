package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleTypes;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;
import java.util.Random;
import java.util.random.RandomGenerator;

public class NuclearGrenadeEntity extends AbstractArrow {
    public NuclearGrenadeEntity(EntityType<NuclearGrenadeEntity> entityType, Level level) {
        super(entityType, level);
    }

    public NuclearGrenadeEntity(LivingEntity shooter, Level level){
        super(ModEntities.NUCLEAR_GRENADE_PROJECTILE.get(), shooter, level, new ItemStack(ModItems.TOMAHAWK.get()), null);
    }



    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        this.explode(result.getEntity().level(), result.getEntity().position());

    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);


        explode(this.level(), this.position());


    }

    private void explode(Level level, Vec3 pos) {

        Color startingColor = new Color(200, 50, 25);
        Color endingColor = new Color(200, 100, 0);
        WorldParticleBuilder particle = WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(1f, 10f, 5f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setTransparencyData(GenericParticleData.create(1f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level().getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(20)
                //.addMotion(0, 0, 0)
                .enableNoClip()
                /*.spawn(level, pos.x, pos.y, pos.z)*/;

        level.addAlwaysVisibleParticle(particle.getParticleOptions(), pos.x,pos.y,pos.z,0,0,0);
        level.addAlwaysVisibleParticle(particle.getParticleOptions(), pos.x,pos.y+0.5f,pos.z,0,0,0);
        level.addAlwaysVisibleParticle(particle.getParticleOptions(), pos.x+1f,pos.y,pos.z+0.5f,0,0,0);
        level.addAlwaysVisibleParticle(particle.getParticleOptions(), pos.x+1f,pos.y,pos.z+0.5f,0,0,0);
        level.addAlwaysVisibleParticle(particle.getParticleOptions(), pos.x,pos.y,pos.z+1f,0,0,0);

        startingColor = new Color(20,20,10);
        endingColor = new Color(0,0,0);
        WorldParticleBuilder smoke = WorldParticleBuilder.create(LodestoneParticleTypes.SMOKE_PARTICLE)
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setScaleData(GenericParticleData.create(2f, 5f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setTransparencyData(GenericParticleData.create(1f,0.7f).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.3f).setSpinOffset((level().getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .enableNoClip()
                .setLifetime(200);
        level.addAlwaysVisibleParticle(smoke.getParticleOptions(), pos.x,pos.y,pos.z, Math.random()*0.2d,Math.random()*0.2d,Math.random()*0.2d);






        if(!level.isClientSide){
            level.explode(this, pos.x,pos.y,pos.z,20, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }


}
