package io.github.qwerty770.mcmod.spmreborn.world.gen.tree;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/**
 * @see net.minecraft.world.level.block.grower.OakTreeGrower
 * @see net.minecraft.data.worldgen.features.TreeFeatures#FANCY_OAK
 */
public final class SweetPotatoTreeFeatures {
    // Update to Minecraft 1.20 -- 2024/11/10  Implemented via data pack
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("tree_features");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA = register("acacia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH = register("birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_BEES_005 = register("birch_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK = register("dark_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK = register("fancy_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_BEES_005 = register("fancy_oak_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_NO_VINE = register("jungle_tree_no_vine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE = register("mega_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_PINE = register("mega_pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_SPRUCE = register("mega_spruce");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK = register("oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BEES_005 = register("oak_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE = register("spruce");

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocationTool.create(SPRMain.MODID, id));
    }
}