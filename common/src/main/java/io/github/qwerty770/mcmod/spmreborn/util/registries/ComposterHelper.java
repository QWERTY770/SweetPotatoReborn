package io.github.qwerty770.mcmod.spmreborn.util.registries;

import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
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
        registerCompostableItem(0.3f, SweetPotatoItems.PEEL.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_OAK_SAPLING_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_SPRUCE_SAPLING_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_BIRCH_SAPLING_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_JUNGLE_SAPLING_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_ACACIA_SAPLING_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_DARK_OAK_SAPLING_ITEM.get());

        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_ACACIA_LEAVES_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_BIRCH_LEAVES_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_DARK_OAK_LEAVES_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_JUNGLE_LEAVES_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_OAK_LEAVES_ITEM.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_SPRUCE_LEAVES_ITEM.get());

        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_WHEAT_SEEDS.get());
        registerCompostableItem(0.3f, SweetPotatoItems.ENCHANTED_BEETROOT_SEEDS.get());
        registerCompostableItem(0.65f, SweetPotatoItems.ENCHANTED_CARROT_ITEM.get());
        registerCompostableItem(0.65f, SweetPotatoItems.ENCHANTED_VANILLA_POTATO_ITEM.get());

        registerCompostableItem(0.5f, SweetPotatoItems.ENCHANTED_SUGAR_CANE_ITEM.get());


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
