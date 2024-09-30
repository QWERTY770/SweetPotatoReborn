package io.github.qwerty770.mcmod.spmreborn.world.gen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EnchantedBirchSaplingGen extends BirchTreeGrower {
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean canSpawnBees) {
        return canSpawnBees ? SweetPotatoTreeFeatures.BIRCH_BEES_005 : SweetPotatoTreeFeatures.BIRCH;
    }
}
