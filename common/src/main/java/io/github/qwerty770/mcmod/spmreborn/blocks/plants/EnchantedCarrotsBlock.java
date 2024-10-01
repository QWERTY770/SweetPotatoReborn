package io.github.qwerty770.mcmod.spmreborn.blocks.plants;

import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import io.github.qwerty770.mcmod.spmreborn.util.tick.RandomTickHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EnchantedCarrotsBlock extends CarrotBlock {
    public EnchantedCarrotsBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SweetPotatoItems.ENCHANTED_CARROT_ITEM.get();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        RandomTickHelper.enchantedCropRandomTick(this, state, world, pos, random);
    }
}
