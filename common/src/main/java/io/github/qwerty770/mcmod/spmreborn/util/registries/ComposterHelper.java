package io.github.qwerty770.mcmod.spmreborn.util.registries;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoComponent;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoStatus;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.jetbrains.annotations.ApiStatus;

public final class ComposterHelper {
    private ComposterHelper() {}

    @ApiStatus.Internal
    public static void register() {
        registerCompostableItem(0.3f, SPRMain.PEEL);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_OAK_SAPLING_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_SPRUCE_SAPLING_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_BIRCH_SAPLING_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_JUNGLE_SAPLING_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_ACACIA_SAPLING_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_DARK_OAK_SAPLING_ITEM);

        registerCompostableItem(0.3f, SPRMain.ENCHANTED_ACACIA_LEAVES_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_BIRCH_LEAVES_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_DARK_OAK_LEAVES_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_JUNGLE_LEAVES_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_OAK_LEAVES_ITEM);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_SPRUCE_LEAVES_ITEM);

        registerCompostableItem(0.3f, SPRMain.ENCHANTED_WHEAT_SEEDS);
        registerCompostableItem(0.3f, SPRMain.ENCHANTED_BEETROOT_SEEDS);
        registerCompostableItem(0.65f, SPRMain.ENCHANTED_CARROT_ITEM);
        registerCompostableItem(0.65f, SPRMain.ENCHANTED_VANILLA_POTATO_ITEM);

        registerCompostableItem(0.5f, SPRMain.ENCHANTED_SUGAR_CANE_ITEM);


        for (SweetPotatoType type: SweetPotatoType.values()) {
            for (SweetPotatoStatus status: SweetPotatoStatus.values()) {
                SweetPotatoComponent component = type.getComponent(status);
                if (component != null) {
                    component.registerCompostableItem(type, status);
                    component.registerGrindableItem(type, status);
                }
            }
        }
    }

    public static void registerCompostableItem(float levelIncreaseChance, ItemLike item) {
        // Update to Minecraft 1.20 -- 2023/12/3
        ComposterBlock.COMPOSTABLES.put(item, levelIncreaseChance);
    }
}
