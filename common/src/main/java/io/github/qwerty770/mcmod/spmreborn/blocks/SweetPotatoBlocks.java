package io.github.qwerty770.mcmod.spmreborn.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.blocks.plants.*;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoType;
import net.minecraft.world.level.block.Block;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.BlockUtils.*;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.block;
import static io.github.qwerty770.mcmod.spmreborn.world.tree.SweetPotatoTreeGrowers.*;

public class SweetPotatoBlocks {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("blocks");

    // Blocks
    public static final RegistrySupplier<Block> MAGIC_CUBE;
    public static final RegistrySupplier<Block> GRINDER;
    public static final RegistrySupplier<Block> SEED_UPDATER;

    // Crops
    public static final RegistrySupplier<Block> PURPLE_POTATO_CROP;
    public static final RegistrySupplier<Block> RED_POTATO_CROP;
    public static final RegistrySupplier<Block> WHITE_POTATO_CROP;

    public static final RegistrySupplier<Block> ENCHANTED_WHEAT_CROP;
    public static final RegistrySupplier<Block> ENCHANTED_BEETROOTS_CROP;
    public static final RegistrySupplier<Block> ENCHANTED_VANILLA_POTATOES_CROP;
    public static final RegistrySupplier<Block> ENCHANTED_CARROTS_CROP;
    public static final RegistrySupplier<Block> ENCHANTED_SUGAR_CANE;

    // Saplings
    public static final RegistrySupplier<Block> ENCHANTED_OAK_SAPLING;
    public static final RegistrySupplier<Block> ENCHANTED_SPRUCE_SAPLING;
    public static final RegistrySupplier<Block> ENCHANTED_BIRCH_SAPLING;
    public static final RegistrySupplier<Block> ENCHANTED_JUNGLE_SAPLING;
    public static final RegistrySupplier<Block> ENCHANTED_ACACIA_SAPLING;
    public static final RegistrySupplier<Block> ENCHANTED_DARK_OAK_SAPLING;

    // Potted Saplings
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_SPRUCE_SAPLING;
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_BIRCH_SAPLING;
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_JUNGLE_SAPLING;
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_ACACIA_SAPLING;
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_OAK_SAPLING;
    public static final RegistrySupplier<Block> POTTED_ENCHANTED_DARK_OAK_SAPLING;

    // Enchanted Leaves
    public static final RegistrySupplier<Block> ENCHANTED_OAK_LEAVES;
    public static final RegistrySupplier<Block> ENCHANTED_ACACIA_LEAVES;
    public static final RegistrySupplier<Block> ENCHANTED_BIRCH_LEAVES;
    public static final RegistrySupplier<Block> ENCHANTED_DARK_OAK_LEAVES;
    public static final RegistrySupplier<Block> ENCHANTED_JUNGLE_LEAVES;
    public static final RegistrySupplier<Block> ENCHANTED_SPRUCE_LEAVES;

    static {
        // Update to Minecraft 1.20 -- 2023/11/26  Deleted util.objsettings.BlockSettings
        // Update to Minecraft 1.21.3 -- 2024/11/22  Update block properties
        // Block
        MAGIC_CUBE = block("magic_cube", MagicCubeBlock::new, createFunctionalBlock(10.0F, 1200.0F));
        GRINDER = block("grinder", GrinderBlock::new, createFunctionalBlock(3.5F, 6.0F));
        SEED_UPDATER = block("agroforestry_table", SeedUpdaterBlock::new, createFunctionalBlock(3.5F, 6.0F));

        // Crops
        PURPLE_POTATO_CROP = block("purple_potatoes", (properties) -> new SweetPotatoesCropBlock(properties, SweetPotatoType.PURPLE), crop);
        RED_POTATO_CROP = block("red_potatoes", (properties) -> new SweetPotatoesCropBlock(properties, SweetPotatoType.RED), crop);
        WHITE_POTATO_CROP = block("white_potatoes", (properties) -> new SweetPotatoesCropBlock(properties, SweetPotatoType.WHITE), crop);
        ENCHANTED_WHEAT_CROP = block("enchanted_wheat", EnchantedWheatBlock::new, crop);
        ENCHANTED_BEETROOTS_CROP = block("enchanted_beetroots", EnchantedBeetrootsBlock::new, crop);
        ENCHANTED_VANILLA_POTATOES_CROP = block("enchanted_potatoes", EnchantedVanillaPotatoesBlock::new, crop);
        ENCHANTED_CARROTS_CROP = block("enchanted_carrots", EnchantedCarrotsBlock::new, crop);
        ENCHANTED_SUGAR_CANE = block("enchanted_sugar_cane", EnchantedSugarCaneBlock::new, grass);
        // Saplings
        ENCHANTED_OAK_SAPLING = createEnchantedSapling("enchanted_oak_sapling", ENCHANTED_OAK_GROWER);
        ENCHANTED_SPRUCE_SAPLING = createEnchantedSapling("enchanted_spruce_sapling", ENCHANTED_SPRUCE_GROWER);
        ENCHANTED_BIRCH_SAPLING = createEnchantedSapling("enchanted_birch_sapling", ENCHANTED_BIRCH_GROWER);
        ENCHANTED_JUNGLE_SAPLING = createEnchantedSapling("enchanted_jungle_sapling", ENCHANTED_JUNGLE_GROWER);
        ENCHANTED_ACACIA_SAPLING = createEnchantedSapling("enchanted_acacia_sapling", ENCHANTED_ACACIA_GROWER);
        ENCHANTED_DARK_OAK_SAPLING = createEnchantedSapling("enchanted_dark_oak_sapling", ENCHANTED_DARK_OAK_GROWER);
        // Potted
        POTTED_ENCHANTED_OAK_SAPLING = createPotted("potted_enchanted_oak_sapling", ENCHANTED_OAK_SAPLING);
        POTTED_ENCHANTED_SPRUCE_SAPLING = createPotted("potted_enchanted_spruce_sapling", ENCHANTED_SPRUCE_SAPLING);
        POTTED_ENCHANTED_BIRCH_SAPLING = createPotted("potted_enchanted_birch_sapling", ENCHANTED_BIRCH_SAPLING);
        POTTED_ENCHANTED_JUNGLE_SAPLING = createPotted("potted_enchanted_jungle_sapling", ENCHANTED_JUNGLE_SAPLING);
        POTTED_ENCHANTED_ACACIA_SAPLING = createPotted("potted_enchanted_acacia_sapling", ENCHANTED_ACACIA_SAPLING);
        POTTED_ENCHANTED_DARK_OAK_SAPLING = createPotted("potted_enchanted_dark_oak_sapling", ENCHANTED_DARK_OAK_SAPLING);
        // Leaves
        ENCHANTED_ACACIA_LEAVES = createLeaves("enchanted_acacia_leaves");
        ENCHANTED_BIRCH_LEAVES = createLeaves("enchanted_birch_leaves");
        ENCHANTED_DARK_OAK_LEAVES = createLeaves("enchanted_dark_oak_leaves");
        ENCHANTED_OAK_LEAVES = createLeaves("enchanted_oak_leaves");
        ENCHANTED_JUNGLE_LEAVES = createLeaves("enchanted_jungle_leaves");
        ENCHANTED_SPRUCE_LEAVES = createLeaves("enchanted_spruce_leaves");
    }
}
