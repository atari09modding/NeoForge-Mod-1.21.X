package net.atari09.atarisnewmegamodproject.block.client;


import java.util.Arrays;
import java.util.Comparator;

public enum LogMineVariant {
    OAK(0),
    DARKOAK(1),
    BIRCH(2),
    SPRUCE(3),
    CHERRY(4),
    MANGROVE(5),
    JUNGLE(6),
    ACACIA(7);


    private static final LogMineVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(LogMineVariant::getId)).toArray(LogMineVariant[]::new);
    private final int id;

    LogMineVariant(int id){
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public static LogMineVariant byId(int id){
        return BY_ID[id % BY_ID.length];
    }
}
