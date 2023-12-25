package me.iipho3nix.iicapemod.utils;

import net.minecraft.util.Identifier;

public class AnimatedCapeData {
    private final Identifier id;
    private final int delay;

    public AnimatedCapeData(Identifier id, int delay) {
        this.id = id;
        this.delay = delay;
    }

    public Identifier getId() {
        return id;
    }

    public int getDelay() {
        return delay;
    }
}
