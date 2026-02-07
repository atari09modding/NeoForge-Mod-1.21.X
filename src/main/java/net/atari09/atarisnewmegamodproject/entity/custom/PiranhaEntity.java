package net.atari09.atarisnewmegamodproject.entity.custom;

import net.atari09.atarisnewmegamodproject.item.ModItems;
import net.atari09.atarisnewmegamodproject.util.ModTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.function.Predicate;


public class PiranhaEntity extends AbstractFish implements GeoEntity {
    public static final Predicate<LivingEntity> TARGETS = target -> target.getType().is(ModTags.Entitys.PIRANHA_ATTACKABLE);
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PiranhaEntity(EntityType<? extends AbstractFish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"idle",0, this::idleAnim));
        controllers.add(new AnimationController<>(this,"swim",0, this::swimAnim));
        controllers.add(new AnimationController<>(this,"attack",0, this::attackAnim).triggerableAnim("attack",RawAnimation.begin().then("animation_ATTACK", Animation.LoopType.PLAY_ONCE)));
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
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1d, true));
        this.goalSelector.addGoal(1, new FishSwimGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, TARGETS));
    }

    public static AttributeSupplier.Builder createAttributtes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }


    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.PIRANHA_BUCKET.get(),1);
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
        if()

        public PiranhaAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }
    }
}
