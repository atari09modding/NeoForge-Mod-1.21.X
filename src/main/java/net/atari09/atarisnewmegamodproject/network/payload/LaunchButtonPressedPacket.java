package net.atari09.atarisnewmegamodproject.network.payload;

import net.atari09.atarisnewmegamodproject.AtariMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public record LaunchButtonPressedPacket(List<Vec2> shots) implements CustomPacketPayload {
    public static final Type<LaunchButtonPressedPacket> TYPE = new Type<>(AtariMod.res("craft_template"));

    public static final StreamCodec<RegistryFriendlyByteBuf, Vec2> VEC2_STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.FLOAT, vec -> vec.x, ByteBufCodecs.FLOAT, vec -> vec.y, Vec2::new);

    public static final StreamCodec<RegistryFriendlyByteBuf,List<Vec2>> SHOTS_STREAM_CODEC =
            ByteBufCodecs.collection(ArrayList::new, VEC2_STREAM_CODEC);

    public static final StreamCodec<RegistryFriendlyByteBuf,LaunchButtonPressedPacket> STREAM_CODEC =
        StreamCodec.composite(SHOTS_STREAM_CODEC,LaunchButtonPressedPacket::shots,LaunchButtonPressedPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
