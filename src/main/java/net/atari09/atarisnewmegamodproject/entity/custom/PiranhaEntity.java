package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.sound.ModSounds;
import net.atari09.atarisnewmegamodproject.util.ModTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.EnumSet;
import java.util.function.Predicate;


public class PiranhaEntity extends AbstractFish implements GeoEntity {
    public static final Predicate<LivingEntity> TARGETS = target -> target.getType().is(ModTags.Entitys.PIRANHA_ATTACKABLE);
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int hunger;

    public PiranhaEntity(EntityType<? extends AbstractFish> entityType, Level level) {
        super(entityType, level);
        hunger = 600;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"idle",0, this::idleAnim));
        controllers.add(new AnimationController<>(this,"swim",0, this::swimAnim));
        controllers.add(new AnimationController<>(this,"attack",0, this::attackAnim).triggerableAnim("animation_ATTACK",RawAnimation.begin().then("animation_ATTACK", Animation.LoopType.PLAY_ONCE)).setAnimationSpeed(4d));
    }

    private PlayState attackAnim(AnimationState<PiranhaEntity> piranhaEntityAnimationState) {
        return PlayState.STOP;
    }

    private PlayState swimAnim(AnimationState<PiranhaEntity> pAnimationState) {
        if(pAnimationState.isMoving()) {
            pAnimationState.getController().setAnimation(RawAnimation.begin().then("animation_SWIM", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private PlayState idleAnim(AnimationState<PiranhaEntity> pAnimationState) {
        pAnimationState.getController().setAnimation(RawAnimation.begin().then("animation_IDLE", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PiranhaAttackGoal(this, 1.5d, true));
        this.goalSelector.addGoal(1, new FishSwimGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Pufferfish.class,true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, TARGETS));
    }

    public static AttributeSupplier.Builder createAttributtes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.FOLLOW_RANGE, 5d)
                .add(Attributes.ATTACK_DAMAGE, 5d);
    }

    @Override
    public void tick() {
        super.tick();
        hunger++;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.PIRANHA_BUCKET.get(),1);
    }

    public int getHunger(){
        return hunger;
    }

    public void setHunger(int hunger){
        this.hunger = hunger;
    }



    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(source.getEntity() != null && !source.isCreativePlayer()){
            setHunger(600);
        }
        return super.hurt(source, amount);
    }

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final AbstractFish fish;

        public FishSwimGoal(AbstractFish fish) {
            super(fish, 1.0, 40);
            this.fish = fish;
        }

        @Override
        public boolean canUse() {
            return super.canUse();
        }
    }

    static class PiranhaAttackGoal extends MeleeAttackGoal{
        @Override
        protected void checkAndPerformAttack(LivingEntity target) {
            if (this.canPerformAttack(target)) {
                this.resetAttackCooldown();
                ((PiranhaEntity) this.mob).triggerAnim("attack","animation_ATTACK");
                this.mob.doHurtTarget(target);
                this.mob.level().playSound(mob,mob.getOnPos(),ModSounds.PIRANHA_ATTACK.get(),SoundSource.HOSTILE,1f,1f);
            }
        }

        @Override
        public void tick() {
            super.tick();
            if (mob.getTarget().isDeadOrDying()){
                ((PiranhaEntity) mob).setHunger(0);
            }
        }

        @Override
        public boolean canUse() {
            return super.canUse() && (mob.getTarget() instanceof Pufferfish || mob.getTarget() instanceof Player || ((PiranhaEntity) mob).getHunger()>=600);
        }

        public PiranhaAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }
    }

}
