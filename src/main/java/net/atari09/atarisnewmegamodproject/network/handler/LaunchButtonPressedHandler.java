package net.atari09.atarisnewmegamodproject.network.handler;

import net.atari09.atarisnewmegamodproject.network.payload.LaunchButtonPressedPacket;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;


public class LaunchButtonPressedHandler {
    public static void handle(final LaunchButtonPressedPacket packet, final IPayloadContext context){
        context.enqueueWork(()->{
            Player player = context.player();
            List<Vec2> coords = packet.shots();
            Level level = player.level();

            for(Vec2 pos : coords){
                double x = pos.x;
                double z = pos.y;

                PrimedTnt tnt = new PrimedTnt(level,player.getX(),player.getY(), player.getZ(),player);

                Vec3 dir = new Vec3(x,1,z).scale(0.1);

                tnt.setDeltaMovement(dir);
                level.addFreshEntity(tnt);

            }

            player.closeContainer();
        });
    }
}





/*
public static void handle(final CraftTemplatePacket packet, final IPayloadContext context){
        context.enqueueWork(()->{
                Player player = context.player();
                ItemStack cost = packet.toCraft().cost;

                if(player.getItemInHand(player.getUsedItemHand()).getItem() instanceof SpecialSmithingTemplateItem item
                        && item.getType(player.getItemInHand(player.getUsedItemHand())) == SpecialSmithingTemplateType.NONE
                        && player.containerMenu instanceof SpecialSmithingTemplateMenu menu){
                    if (menu.container.getItem(0).is(cost.getItem()) && menu.container.getItem(0).getCount() >= cost.getCount()){
                        menu.container.getItem(0).shrink(cost.getCount());
                        player.getItemInHand(player.getUsedItemHand()).set(ModDataComponents.SPECIALSMITHINGTEMPLATETYPES, packet.toCraft());
                        player.closeContainer();
                    }
                }
            }
        );

    }
 */