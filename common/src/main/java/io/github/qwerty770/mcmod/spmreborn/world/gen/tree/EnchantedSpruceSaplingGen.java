package io.github.qwerty770.mcmod.spmreborn.world.gen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EnchantedSpruceSaplingGen extends SpruceTreeGrower {
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return SweetPotatoTreeFeatures.SPRUCE;
    }

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return random.nextBoolean() ? SweetPotatoTreeFeatures.MEGA_SPRUCE : SweetPotatoTreeFeatures.MEGA_PINE;
    }
}
