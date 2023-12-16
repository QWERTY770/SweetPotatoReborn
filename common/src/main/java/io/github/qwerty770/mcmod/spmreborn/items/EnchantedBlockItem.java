package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

@StableApi
public class EnchantedBlockItem extends BlockItem {
    public EnchantedBlockItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @ApiStatus.Internal
    public static EnchantedBlockItem of(String id, Block original, Properties settings) {
        return (EnchantedBlockItem) RegistryHelper.item(id, new EnchantedBlockItem(original, settings));
    }
}
