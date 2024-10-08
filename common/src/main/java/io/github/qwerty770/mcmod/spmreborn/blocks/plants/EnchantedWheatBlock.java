package io.github.qwerty770.mcmod.spmreborn.blocks.plants;

import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import io.github.qwerty770.mcmod.spmreborn.util.tick.RandomTickHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EnchantedWheatBlock extends CropBlock {
    public EnchantedWheatBlock(Properties settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SweetPotatoItems.ENCHANTED_WHEAT_SEEDS.get();
    }

    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel world, BlockPos pos, RandomSource random) {
        RandomTickHelper.enchantedCropRandomTick(this, state, world, pos, random);
    }
}
