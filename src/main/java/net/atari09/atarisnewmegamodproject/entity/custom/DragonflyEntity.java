package net.atari09.atarisnewmegamodproject.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.UUID;

public class DragonflyEntity extends Animal implements FlyingAnimal, GeoEntity {
    AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean flying;
    private boolean tired;
    private int energy;

    public DragonflyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.flying = true;
        this.tired = true;
        this.energy = 0;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return this.flying;
    }

    private void setFlying(boolean f) {
        this.flying = f;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this,level);
    }

    @Override
    public void tick() {
        super.tick();
        energy--;
        if(!tired && random.nextInt()==1){
            tired = true;
        }

    }


    @Override
    protected double getDefaultGravity() {
        return 0d;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this,1.5));
        this.goalSelector.addGoal(2, new SitOnLilyPadGoal(this,2f,5));
        this.goalSelector.addGoal(3, new MoveToBlockGoal(this, 2f, 30) {
            @Override
            protected boolean isValidTarget(LevelReader level, BlockPos pos) {
                return (level.getBlockState(pos).is(Blocks.AIR) && level.getBlockState(pos.below(2)).is(Blocks.WATER));
            }
        });
        this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this,2f));

    }

    public static AttributeSupplier.Builder createAttributtes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1d)
                .add(Attributes.MOVEMENT_SPEED, 2d)
                .add(Attributes.FOLLOW_RANGE, 1d)
                .add(Attributes.ATTACK_DAMAGE, 0d);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "animation_controller",0, this::predicate)
                .triggerableAnim("lift_off", RawAnimation.begin().thenPlay("lift_off"))
                .triggerableAnim("land", RawAnimation.begin().thenPlay("land"))
        );
    }

    private PlayState predicate(AnimationState<DragonflyEntity> state) {
        return state.setAndContinue(isFlying() ? RawAnimation.begin().thenLoop("fly") : RawAnimation.begin().thenLoop("idle_ground"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private class SitOnLilyPadGoal extends MoveToBlockGoal{
        private final DragonflyEntity dragonfly;
        public SitOnLilyPadGoal(DragonflyEntity mob, double speedModifier, int searchRange) {
            super(mob, speedModifier, searchRange);
            dragonfly = mob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && dragonfly.tired;
        }

        @Override
        public void tick() {
            super.tick();
            dragonfly.setFlying(this.isReachedTarget());
            dragonfly.tired = false;
            dragonfly.energy = 400;
        }

        @Override
        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            if (!level.isEmptyBlock(pos.above())) {
                return false;
            } else {
                return (level.getBlockState(pos).is(Blocks.LILY_PAD));
            }
        }

        @Override
        public void start() {
            super.start();
            dragonfly.setFlying(false);
        }

        @Override
        public void stop() {
            super.stop();
            dragonfly.setFlying(false);
        }
    }


}
