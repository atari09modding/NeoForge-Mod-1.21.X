package net.atari09.atarisnewmegamodproject.item.custom;


import net.atari09.atarisnewmegamodproject.entity.custom.MagicProjectileEntity;
import net.atari09.atarisnewmegamodproject.entity.custom.TomahawkProjectileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicWandItem extends Item {


    public MagicWandItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            MagicProjectileEntity magicProjectile = new MagicProjectileEntity(pPlayer, pLevel);
            magicProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 0F);
            pLevel.addFreshEntity(magicProjectile);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.hurtAndBreak(1,pPlayer, null);
            }
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));


        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
