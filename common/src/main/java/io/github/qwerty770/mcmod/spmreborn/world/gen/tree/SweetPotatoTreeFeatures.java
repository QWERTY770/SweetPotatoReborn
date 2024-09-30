package io.github.qwerty770.mcmod.spmreborn.world.gen.tree;

import com.google.common.collect.ImmutableList;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.util.registries.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.util.registries.ResourceLocationTool;
import io.github.qwerty770.mcmod.spmreborn.util.world.SimpleStateProviderTool;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;

import java.util.List;
import java.util.OptionalInt;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.featureRegistry;
import static io.github.qwerty770.mcmod.spmreborn.world.gen.tree.SweetPotatoTreeFeatures.Constants.*;

/**
 * @see net.minecraft.world.level.block.grower.OakTreeGrower
 * @see net.minecraft.data.worldgen.features.TreeFeatures#FANCY_OAK
 */
public final class SweetPotatoTreeFeatures {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("tree_features");

    private static <FC extends TreeConfiguration> ResourceKey<ConfiguredFeature<?, ?>> register(String id, FC featureConfig) {
        // Temporary solution
        featureRegistry.register(id, () -> new ConfiguredFeature<>(Feature.TREE, featureConfig));
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocationTool.create(SPRMain.MODID, id));
    }

    // Update to Minecraft 1.20 -- 2023/12/16
    public static final ResourceKey<ConfiguredFeature<?, ?>>
            FANCY_OAK, FANCY_OAK_BEES_005, OAK, OAK_BEES_005,
            SPRUCE, MEGA_SPRUCE, MEGA_PINE,
            BIRCH, BIRCH_BEES_005,
            JUNGLE_TREE_NO_VINE, MEGA_JUNGLE_TREE,
            ACACIA, DARK_OAK;

    static {
        FANCY_OAK = register("fancy_oak", largeOak().build());
        FANCY_OAK_BEES_005 = register("fancy_oak_bees_005",
                largeOak().decorators(List.of(MORE_BEEHIVES_TREES)).build());
        OAK = register("oak", (oak().build()));
        OAK_BEES_005 = register("oak_bees_005",
                oak().decorators(List.of(MORE_BEEHIVES_TREES)).build());
        SPRUCE = register("spruce",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(SPRUCE_LOG),
                        new StraightTrunkPlacer(5, 2, 1),
                        new SimpleStateProviderTool(ENCHANTED_SPRUCE_LEAVES),
                        new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                        new TwoLayersFeatureSize(2, 0, 2)))
                        .ignoreVines().build()));
        MEGA_SPRUCE = register("mega_spruce",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(SPRUCE_LOG),
                        new GiantTrunkPlacer(13, 2, 14),
                        new SimpleStateProviderTool(ENCHANTED_SPRUCE_LEAVES),
                        new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)),
                        new TwoLayersFeatureSize(1, 1, 2)))
                        .decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(PODZOL))))
                        .build()));
        MEGA_PINE = register("mega_pine",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(SPRUCE_LOG),
                        new GiantTrunkPlacer(13, 2, 14),
                        new SimpleStateProviderTool(ENCHANTED_SPRUCE_LEAVES),
                        new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 7)),
                        new TwoLayersFeatureSize(1, 1, 2)))
                        .decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(PODZOL))))
                        .build()));
        BIRCH = register("birch", (birch().build()));
        BIRCH_BEES_005 = register("birch_bees_005",
                birch().decorators(List.of(MORE_BEEHIVES_TREES)).build());
        JUNGLE_TREE_NO_VINE = register("jungle_tree_no_vine",
                jungle().ignoreVines().build());
        MEGA_JUNGLE_TREE = register("mega_jungle_tree",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(JUNGLE_LOG),
                        new MegaJungleTrunkPlacer(10, 2, 19),
                        new SimpleStateProviderTool(ENCHANTED_JUNGLE_LEAVES),
                        new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 1, 2)))
                        .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                        .build()));
        ACACIA = register("acacia",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ACACIA_LOG),
                        new ForkingTrunkPlacer(5, 2, 2),
                        new SimpleStateProviderTool(ENCHANTED_ACACIA_LEAVES),
                        new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2)))
                        .ignoreVines().build()));
        DARK_OAK = register("dark_oak",
                ((new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DARK_OAK_LOG),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        new SimpleStateProviderTool(ENCHANTED_DARK_OAK_LEAVES),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())))
                        .ignoreVines().build()));
    }

    static final class Constants {
        public static final BlockState OAK_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_OAK_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_OAK_SAPLING;

        public static final BlockState SPRUCE_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_SPRUCE_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_SPRUCE_SAPLING;
        public static final BlockState PODZOL;

        public static final BlockState BIRCH_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_BIRCH_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_BIRCH_SAPLING;

        public static final BlockState JUNGLE_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_JUNGLE_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_JUNGLE_SAPLING;

        public static final BlockState ACACIA_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_ACACIA_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_ACACIA_SAPLING;

        public static final BlockState DARK_OAK_LOG;
        public static final RegistrySupplier<Block> ENCHANTED_DARK_OAK_LEAVES;
        public static final RegistrySupplier<Block> ENCHANTED_DARK_OAK_SAPLING;

        static {
            OAK_LOG = Blocks.OAK_LOG.defaultBlockState();
            ENCHANTED_OAK_LEAVES = SweetPotatoBlocks.ENCHANTED_OAK_LEAVES;
            ENCHANTED_OAK_SAPLING = SweetPotatoBlocks.ENCHANTED_OAK_SAPLING;

            SPRUCE_LOG = Blocks.SPRUCE_LOG.defaultBlockState();
            ENCHANTED_SPRUCE_LEAVES = SweetPotatoBlocks.ENCHANTED_SPRUCE_LEAVES;
            ENCHANTED_SPRUCE_SAPLING = SweetPotatoBlocks.ENCHANTED_SPRUCE_SAPLING;
            PODZOL = Blocks.PODZOL.defaultBlockState();

            BIRCH_LOG = Blocks.BIRCH_LOG.defaultBlockState();
            ENCHANTED_BIRCH_LEAVES = SweetPotatoBlocks.ENCHANTED_BIRCH_LEAVES;
            ENCHANTED_BIRCH_SAPLING = SweetPotatoBlocks.ENCHANTED_BIRCH_SAPLING;

            JUNGLE_LOG = Blocks.JUNGLE_LOG.defaultBlockState();
            ENCHANTED_JUNGLE_LEAVES = SweetPotatoBlocks.ENCHANTED_JUNGLE_LEAVES;
            ENCHANTED_JUNGLE_SAPLING = SweetPotatoBlocks.ENCHANTED_JUNGLE_SAPLING;

            ACACIA_LOG = Blocks.ACACIA_LOG.defaultBlockState();
            ENCHANTED_ACACIA_LEAVES = SweetPotatoBlocks.ENCHANTED_ACACIA_LEAVES;
            ENCHANTED_ACACIA_SAPLING = SweetPotatoBlocks.ENCHANTED_ACACIA_SAPLING;

            DARK_OAK_LOG = Blocks.DARK_OAK_LOG.defaultBlockState();
            ENCHANTED_DARK_OAK_LEAVES = SweetPotatoBlocks.ENCHANTED_DARK_OAK_LEAVES;
            ENCHANTED_DARK_OAK_SAPLING = SweetPotatoBlocks.ENCHANTED_DARK_OAK_SAPLING;
        }

        public static final BeehiveDecorator MORE_BEEHIVES_TREES = new BeehiveDecorator(0.05F);
    }

    private static TreeConfiguration.TreeConfigurationBuilder largeOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(OAK_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                new SimpleStateProviderTool(ENCHANTED_OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))))
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder tree(Block trunkBlock, RegistrySupplier<Block> foliageBlock, int baseHeight, int firstRandomHeight) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(trunkBlock),
                new StraightTrunkPlacer(baseHeight, firstRandomHeight, 0),
                new SimpleStateProviderTool(foliageBlock),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder oak() {
        return tree(Blocks.OAK_LOG, ENCHANTED_OAK_LEAVES, 4, 2)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder birch() {
        return tree(Blocks.BIRCH_LOG, ENCHANTED_BIRCH_LEAVES, 5, 2)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder jungle() {
        return tree(Blocks.JUNGLE_LOG, ENCHANTED_JUNGLE_LEAVES, 4, 8);
    }
}