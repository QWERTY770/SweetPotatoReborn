package io.github.qwerty770.mcmod.spmreborn.items.sweetpotato;

import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.stream.Stream;

public enum SweetPotatoType {
    // Update to Minecraft 1.20 -- 2023/12/16
    PURPLE(
            new SweetPotatoComponent(3, 6.0f, 0.35f, 3.0d),
            new SweetPotatoComponent(8, 9.6f, 0.10f),
            new SweetPotatoComponent(7, 8.6f, 0.60f, 5.0d, true)),
    RED(
            new SweetPotatoComponent(4, 5.0f, 0.30f, 2.6d),
            new SweetPotatoComponent(7, 9.0f, 0.10f),
            new SweetPotatoComponent(6, 8.0f, 0.55f, 5.0d, true)
    ),
    WHITE(
            new SweetPotatoComponent(2, 4.0f, 0.25f, 2.2d),
            new SweetPotatoComponent(7, 9.3f, 0.10f),
            new SweetPotatoComponent(6, 8.3f, 0.50f, 5.0d, true)
    );

    private final SweetPotatoComponent raw;
    private final SweetPotatoComponent baked;
    private final SweetPotatoComponent enchanted;

    SweetPotatoType(SweetPotatoComponent raw, SweetPotatoComponent baked, SweetPotatoComponent enchanted) {
        this.raw = raw;
        this.baked = baked;
        this.enchanted = enchanted;
    }

    public SweetPotatoComponent getComponent(SweetPotatoStatus status) {
        return switch (status) {
            case RAW -> raw;
            case BAKED -> baked;
            case ENCHANTED -> enchanted;
            default -> null;
        };
    }

    public ItemLike getRaw() {
        return switch (this) {
            case PURPLE -> SweetPotatoItems.PURPLE_POTATO.get();
            case RED -> SweetPotatoItems.RED_POTATO.get();
            case WHITE -> SweetPotatoItems.WHITE_POTATO.get();
        };
    }

    public ItemLike getBaked() {
        return switch (this) {
            case PURPLE -> SweetPotatoItems.BAKED_PURPLE_POTATO.get();
            case RED -> SweetPotatoItems.BAKED_RED_POTATO.get();
            case WHITE -> SweetPotatoItems.BAKED_WHITE_POTATO.get();
        };
    }

    public Block getCrop() {
        return switch (this) {
            case PURPLE -> SweetPotatoBlocks.PURPLE_POTATO_CROP.get();
            case RED -> SweetPotatoBlocks.RED_POTATO_CROP.get();
            case WHITE -> SweetPotatoBlocks.WHITE_POTATO_CROP.get();
        };
    }

    public ItemLike getEnchanted() {
        return switch (this) {
            case PURPLE -> SweetPotatoItems.ENCHANTED_PURPLE_POTATO.get();
            case RED -> SweetPotatoItems.ENCHANTED_RED_POTATO.get();
            case WHITE -> SweetPotatoItems.ENCHANTED_WHITE_POTATO.get();
        };
    }

    public ItemLike get(SweetPotatoStatus status) {
        return switch (status) {
            case RAW -> this.getRaw();
            case BAKED -> this.getBaked();
            case ENCHANTED -> this.getEnchanted();
            case CROP -> this.getCrop();
        };
    }

    public Stream<SweetPotatoType> getOtherTwo() {
        return Arrays.stream(values()).filter(type -> this != type);
    }
}
