package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleTypes;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;

public class MagicProjectileEntity extends AbstractArrow {
    private int lifetime = 200;
    private int life;

    public MagicProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public MagicProjectileEntity(LivingEntity shooter, Level level){
        super(ModEntities.MAGIC_PROJECTILE.get(),shooter, level, new ItemStack(Items.AIR), null);
    }

    public boolean isGrounded() {
        return inGround;
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
        this.kill();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.kill();
        super.onHitBlock(result);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0d;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 d = this.getDeltaMovement();
        if (d.lengthSqr() < 1.0E-6) return;

// 1. Flugrichtung normalisieren
        d = d.normalize();

// 2. Welt-Up (Fallback, falls Projektil fast senkrecht fliegt)
        Vec3 worldUp = Math.abs(d.y) > 0.9
                ? new Vec3(1, 0, 0)
                : new Vec3(0, 1, 0);

// 3. Seitlicher Vektor (rechtwinklig zur Flugrichtung)
        Vec3 side = d.cross(worldUp).normalize();

// 4. "Rotiertes Up" → DAS ist dein gesuchter Vektor
        Vec3 rotatedUp = side.cross(d).normalize();

// Stärke der Partikelbewegung
        double strength = 0.25;

// 5. Partikel nach oben
        this.level().addParticle(
                ParticleTypes.GLOW_SQUID_INK,
                this.getX(), this.getY(), this.getZ(),
                rotatedUp.x * strength,
                rotatedUp.y * strength,
                rotatedUp.z * strength
        );

// 6. Partikel nach unten
        this.level().addParticle(
                ParticleTypes.GLOW_SQUID_INK,
                this.getX(), this.getY(), this.getZ(),
                -rotatedUp.x * strength,
                -rotatedUp.y * strength,
                -rotatedUp.z * strength
        );
        this.level().addParticle(ParticleTypes.GLOW,this.getX(),this.getY(),this.getZ(),0d,0.0d,0d);
        if(this.isInWater()){
            this.kill();
        }

        Vec3 pos = this.position();
        Color startingColor = new Color(100, 255, 100);
        Color endingColor = new Color(0, 100, 200);
        WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level().getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(40)
                .addMotion(0, 0.01f, 0)
                .enableNoClip()
                .enableForcedSpawn()
                .spawn(level(), pos.x, pos.y, pos.z);

    }

    @Override
    protected void tickDespawn() {
        this.life++;
        if (this.life >= lifetime) {
            this.discard();
        }
    }
}
