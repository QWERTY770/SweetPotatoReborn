package io.github.qwerty770.mcmod.spmreborn.world.gen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EnchantedOakSaplingGen extends OakTreeGrower {
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean canSpawnBees) {
        if (random.nextInt(10) == 0) {
            return canSpawnBees ? SweetPotatoTreeFeatures.FANCY_OAK_BEES_005 : SweetPotatoTreeFeatures.FANCY_OAK;
        } else {
            return canSpawnBees ? SweetPotatoTreeFeatures.OAK_BEES_005 : SweetPotatoTreeFeatures.OAK;
        }
    }
}
