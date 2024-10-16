package io.github.qwerty770.mcmod.spmreborn.items;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;

import static io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks.*;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.*;

public class SweetPotatoItems {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("items");

    public static final CreativeModeTab SPR_ITEMS = CreativeTabRegistry.create(Component.translatable("tab.spmreborn"),
            () -> SweetPotatoItems.BAKED_PURPLE_POTATO.get().getDefaultInstance());
    public static final RegistrySupplier<CreativeModeTab> SPR_ITEMS_SUPPLIER = creativeModeTab("spr_items", SPR_ITEMS);

    // Items
    public static final RegistrySupplier<Item> PEEL;
    // Baked Potatoes
    public static final RegistrySupplier<Item> BAKED_PURPLE_POTATO;
    public static final RegistrySupplier<Item> BAKED_RED_POTATO;
    public static final RegistrySupplier<Item> BAKED_WHITE_POTATO;
    // Raw Potatoes
    public static final RegistrySupplier<Item> PURPLE_POTATO;
    public static final RegistrySupplier<Item> RED_POTATO;
    public static final RegistrySupplier<Item> WHITE_POTATO;
    // Enchanted Potatoes
    public static final RegistrySupplier<Item> ENCHANTED_PURPLE_POTATO;
    public static final RegistrySupplier<Item> ENCHANTED_RED_POTATO;
    public static final RegistrySupplier<Item> ENCHANTED_WHITE_POTATO;
    // Misc
    public static final RegistrySupplier<Item> POTATO_POWDER;
    public static final RegistrySupplier<Item> XMAS_TREATING_BOWL;

    // Block Items
    public static final RegistrySupplier<BlockItem> MAGIC_CUBE_ITEM;
    public static final RegistrySupplier<BlockItem> GRINDER_ITEM;
    public static final RegistrySupplier<BlockItem> SEED_UPDATER_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_OAK_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_SPRUCE_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_BIRCH_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_JUNGLE_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_ACACIA_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_DARK_OAK_SAPLING_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_WHEAT_SEEDS;
    public static final RegistrySupplier<Item> ENCHANTED_BEETROOT_SEEDS;
    public static final RegistrySupplier<Item> ENCHANTED_VANILLA_POTATO_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_CARROT_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_OAK_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_ACACIA_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_BIRCH_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_DARK_OAK_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_JUNGLE_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_SPRUCE_LEAVES_ITEM;
    public static final RegistrySupplier<Item> ENCHANTED_SUGAR_CANE_ITEM;

    static {
        // Update to Minecraft 1.20 -- 2023/10/30  Deleted util.objsettings.ItemSettings
        // Item
        PEEL = defaultItem("peel", defaultProp());
        POTATO_POWDER = defaultItem("potato_powder", defaultProp());
        XMAS_TREATING_BOWL = defaultItem("treating_bowl", new Item.Properties());
        BAKED_PURPLE_POTATO = item("baked_purple_potato", new BakedSweetPotatoItem(defaultProp(), SweetPotatoType.PURPLE));
        BAKED_RED_POTATO = item("baked_red_potato", new BakedSweetPotatoItem(defaultProp(), SweetPotatoType.RED));
        BAKED_WHITE_POTATO = item("baked_white_potato", new BakedSweetPotatoItem(defaultProp(), SweetPotatoType.WHITE));
        ENCHANTED_PURPLE_POTATO = item("enchanted_purple_potato", new EnchantedSweetPotatoItem(defaultProp().stacksTo(1), SweetPotatoType.PURPLE));
        ENCHANTED_RED_POTATO = item("enchanted_red_potato", new EnchantedSweetPotatoItem(defaultProp().stacksTo(1), SweetPotatoType.RED));
        ENCHANTED_WHITE_POTATO = item("enchanted_white_potato", new EnchantedSweetPotatoItem(defaultProp().stacksTo(1), SweetPotatoType.WHITE));
        // Block Items
        PURPLE_POTATO = item("purple_potato", () -> new RawSweetPotatoBlockItem(PURPLE_POTATO_CROP.get(), defaultProp(), SweetPotatoType.PURPLE));
        RED_POTATO = item("red_potato", () -> new RawSweetPotatoBlockItem(RED_POTATO_CROP.get(), defaultProp(), SweetPotatoType.RED));
        WHITE_POTATO = item("white_potato", () -> new RawSweetPotatoBlockItem(WHITE_POTATO_CROP.get(), defaultProp(), SweetPotatoType.WHITE));

        ENCHANTED_WHEAT_SEEDS = AliasedEnchantedItem.of("enchanted_wheat_seeds", ENCHANTED_WHEAT_CROP, defaultProp());
        ENCHANTED_BEETROOT_SEEDS = AliasedEnchantedItem.of("enchanted_beetroot_seeds", ENCHANTED_BEETROOTS_CROP, defaultProp());
        ENCHANTED_VANILLA_POTATO_ITEM = AliasedEnchantedItem.ofFood("enchanted_potato", ENCHANTED_VANILLA_POTATOES_CROP, Foods.POTATO, defaultProp());
        ENCHANTED_CARROT_ITEM = AliasedEnchantedItem.ofFood("enchanted_carrot", ENCHANTED_CARROTS_CROP, Foods.CARROT, defaultProp());
        ENCHANTED_SUGAR_CANE_ITEM = EnchantedBlockItem.of("enchanted_sugar_cane", ENCHANTED_SUGAR_CANE, defaultProp());

        ENCHANTED_ACACIA_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_acacia_leaves", ENCHANTED_ACACIA_LEAVES, defaultProp());
        ENCHANTED_BIRCH_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_birch_leaves", ENCHANTED_BIRCH_LEAVES, defaultProp());
        ENCHANTED_DARK_OAK_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_dark_oak_leaves", ENCHANTED_DARK_OAK_LEAVES, defaultProp());
        ENCHANTED_JUNGLE_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_jungle_leaves", ENCHANTED_JUNGLE_LEAVES, defaultProp());
        ENCHANTED_OAK_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_oak_leaves", ENCHANTED_OAK_LEAVES, defaultProp());
        ENCHANTED_SPRUCE_LEAVES_ITEM = EnchantedBlockItem.of("enchanted_spruce_leaves", ENCHANTED_SPRUCE_LEAVES, defaultProp());

        // Functional Blocks' Items
        MAGIC_CUBE_ITEM = blockItem("magic_cube", MAGIC_CUBE, defaultProp());
        GRINDER_ITEM = blockItem("grinder", GRINDER, defaultProp());
        SEED_UPDATER_ITEM = blockItem("agroforestry_table", SEED_UPDATER, defaultProp());
        ENCHANTED_OAK_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_oak_sapling", ENCHANTED_OAK_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
        ENCHANTED_SPRUCE_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_spruce_sapling", ENCHANTED_SPRUCE_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
        ENCHANTED_BIRCH_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_birch_sapling", ENCHANTED_BIRCH_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
        ENCHANTED_JUNGLE_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_jungle_sapling", ENCHANTED_JUNGLE_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
        ENCHANTED_ACACIA_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_acacia_sapling", ENCHANTED_ACACIA_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
        ENCHANTED_DARK_OAK_SAPLING_ITEM = EnchantedBlockItem.of("enchanted_dark_oak_sapling", ENCHANTED_DARK_OAK_SAPLING, defaultProp().rarity(Rarity.UNCOMMON));
    }
    
    public static Item.Properties defaultProp(){
        return new Item.Properties().arch$tab(SPR_ITEMS_SUPPLIER);
    }
}
