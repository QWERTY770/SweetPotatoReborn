package io.github.qwerty770.mcmod.spmreborn.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.blocks.plants.*;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoType;
import io.github.qwerty770.mcmod.spmreborn.world.gen.tree.*;
import net.minecraft.world.level.block.Block;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.BlockUtils.*;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.block;

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
        // Update to Minecraft 1.20 -- 2023/11/26
        // Deleted util.objsettings.BlockSettings
        // Block
        MAGIC_CUBE = block("magic_cube", new MagicCubeBlock(createFunctionalBlock(10.0F, 1200.0F)));
        GRINDER = block("grinder", new GrinderBlock(createFunctionalBlock(3.5F, 6.0F)));
        SEED_UPDATER = block("agroforestry_table", new SeedUpdaterBlock(createFunctionalBlock(3.5F, 6.0F)));

        // Crops
        PURPLE_POTATO_CROP = block("purple_potatoes", new SweetPotatoesCropBlock(crop, SweetPotatoType.PURPLE));
        RED_POTATO_CROP = block("red_potatoes", new SweetPotatoesCropBlock(crop, SweetPotatoType.RED));
        WHITE_POTATO_CROP = block("white_potatoes", new SweetPotatoesCropBlock(crop, SweetPotatoType.WHITE));
        ENCHANTED_WHEAT_CROP = block("enchanted_wheat", new EnchantedWheatBlock(crop));
        ENCHANTED_BEETROOTS_CROP = block("enchanted_beetroots", new EnchantedBeetrootsBlock(crop));
        ENCHANTED_VANILLA_POTATOES_CROP = block("enchanted_potatoes", new EnchantedVanillaPotatoesBlock(crop));
        ENCHANTED_CARROTS_CROP = block("enchanted_carrots", new EnchantedCarrotsBlock(crop));
        ENCHANTED_SUGAR_CANE = block("enchanted_sugar_cane", new EnchantedSugarCaneBlock(grass));
        // Saplings
        ENCHANTED_OAK_SAPLING = createEnchantedSapling("enchanted_oak_sapling", EnchantedOakSaplingGen::new);
        ENCHANTED_SPRUCE_SAPLING = createEnchantedSapling("enchanted_spruce_sapling", EnchantedSpruceSaplingGen::new);
        ENCHANTED_BIRCH_SAPLING = createEnchantedSapling("enchanted_birch_sapling", EnchantedBirchSaplingGen::new);
        ENCHANTED_JUNGLE_SAPLING = createEnchantedSapling("enchanted_jungle_sapling", EnchantedJungleSaplingGen::new);
        ENCHANTED_ACACIA_SAPLING = createEnchantedSapling("enchanted_acacia_sapling", EnchantedAcaciaSaplingGen::new);
        ENCHANTED_DARK_OAK_SAPLING = createEnchantedSapling("enchanted_dark_oak_sapling", EnchantedDarkOakSaplingGen::new);
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
