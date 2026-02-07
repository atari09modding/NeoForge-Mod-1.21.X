package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.entity.ModEntities;
import net.atari09.atarisnewmegamodproject.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class BrokEntity extends Monster implements RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BrokEntity.class, EntityDataSerializers.BOOLEAN);
    private LivingEntity TARGET;

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = p_348303_ -> !p_348303_.getType().is(ModTags.Entitys.BROK_FRIENDS)
            && p_348303_.attackable();

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("BROK"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_10);

    public BrokEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
       this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.0, 60, 20.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, true, false, LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this,1f));

    }


    public static AttributeSupplier.Builder createAttributtes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300d)
                .add(Attributes.MOVEMENT_SPEED, 0.5d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }


    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        Level level = target.level();
        if(!level.isClientSide()){
            setAttacking(true);
            TARGET = target;
            attackAnimationTimeout = 34;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()){
            this.setupAnimationStates();
        } else {
            --attackAnimationTimeout;
        }
        if (!this.level().isClientSide && this.getAttacking() && attackAnimationTimeout <= 0) {
            Entity stone = new BrokStone(ModEntities.BROK_STONE.get(), this.level());
            stone.setPos(TARGET.getX(), TARGET.getY() + 10, TARGET.getZ());
            this.level().addFreshEntity(stone);

            setAttacking(false);
            TARGET = null;
        }

    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this,level);
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 10;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.getAttacking() && this.attackAnimationTimeout <= 0){
            this.attackAnimationTimeout = 34;
            this.attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!getAttacking()){
            this.attackAnimationState.stop();
            this.attackAnimationTimeout = 0;
        }
    }



    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING,attacking);
    }

    public boolean getAttacking(){
        return this.entityData.get(ATTACKING);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth()/this.getMaxHealth());
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.STONE_BREAK;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }
}
