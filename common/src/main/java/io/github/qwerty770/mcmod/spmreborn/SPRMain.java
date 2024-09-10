package io.github.qwerty770.mcmod.spmreborn;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.blocks.GrinderBlock;
import io.github.qwerty770.mcmod.spmreborn.blocks.MagicCubeBlock;
import io.github.qwerty770.mcmod.spmreborn.blocks.SeedUpdaterBlock;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoesCropBlock;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.GrinderBlockEntity;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.MagicCubeBlockEntity;
import io.github.qwerty770.mcmod.spmreborn.blocks.plants.*;
import io.github.qwerty770.mcmod.spmreborn.items.*;
import io.github.qwerty770.mcmod.spmreborn.linkage.SPRLinkage;
import io.github.qwerty770.mcmod.spmreborn.loot.SPRLootTables;
import io.github.qwerty770.mcmod.spmreborn.loot.SetEnchantedPotatoEffectFunction;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipe;
import io.github.qwerty770.mcmod.spmreborn.magic.MagicalEnchantmentLoader;
import io.github.qwerty770.mcmod.spmreborn.screen.GrinderScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.screen.MagicCubeScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.screen.SeedUpdaterScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.registries.AnimalIngredients;
import io.github.qwerty770.mcmod.spmreborn.util.registries.ComposterHelper;
import io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoType;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagContainer;
import io.github.qwerty770.mcmod.spmreborn.world.gen.tree.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.food.Foods;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.BlockUtils.*;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.*;

@SuppressWarnings("unused")
@StableApi
public class SPRMain implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("Sweet Potato Reborn");

	public static final String MODID = "spmreborn";

	// Update to Minecraft 1.20 -- 2023/10/30
	// Creative mode tab
	public static final CreativeModeTab SPR_ITEMS = CreativeTabRegistry.create(Component.translatable("tab.spmreborn"),
			() -> new ItemStack(new EnchantedSweetPotatoItem(new Item.Properties(), SweetPotatoType.PURPLE)));

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

	// -*- -*- MISC -*- -*- //
	// Screen Handlers
	public static final RegistrySupplier<MenuType<SeedUpdaterScreenHandler>> SEED_UPDATER_SCREEN_HANDLER_TYPE;
	public static final RegistrySupplier<MenuType<GrinderScreenHandler>> GRINDER_SCREEN_HANDLER_TYPE;
	public static final RegistrySupplier<MenuType<MagicCubeScreenHandler>> MAGIC_CUBE_SCREEN_HANDLER_TYPE;

	// Recipe Serializer
	public static final RegistrySupplier<RecipeSerializer<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_SERIALIZER;

	// Recipe Type
	public static final RegistrySupplier<RecipeType<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_TYPE;

	// Block Entities
	public static final RegistrySupplier<BlockEntityType<GrinderBlockEntity>> GRINDER_BLOCK_ENTITY_TYPE;
	public static final RegistrySupplier<BlockEntityType<MagicCubeBlockEntity>> MAGIC_CUBE_BLOCK_ENTITY_TYPE;

	// Item Tags
	public static final TagContainer<Item> RAW_SWEET_POTATOES;
	public static final TagContainer<Item> ENCHANTED_SWEET_POTATOES;
	public static final TagContainer<Item> ALL_SWEET_POTATOES;
	// About Pigs & Parrots
	public static final TagContainer<Item> PIG_BREEDING_INGREDIENTS;
    public static final TagContainer<Item> CHICKEN_BREEDING_INGREDIENTS;

	// Sounds
	public static final RegistrySupplier<SoundEvent> AGROFORESTRY_TABLE_FINISH;
	public static final RegistrySupplier<SoundEvent> GRINDER_GRIND;
	public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_ACTIVATE;
	public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_DEACTIVATE;
	public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_AMBIENT;

	// Stats
	public static final ResourceLocation INTERACT_WITH_GRINDER;
	public static final ResourceLocation INTERACT_WITH_AGRO;
	public static final ResourceLocation CROP_UPGRADED;
	public static final ResourceLocation SWEET_POTATO_EATEN;
	public static final ResourceLocation INTERACT_WITH_MAGIC_CUBE;

	// Loot
	public static final RegistrySupplier<LootItemFunctionType> SET_ENCHANTED_POTATO_EFFECT;

	@Override
	public void onInitialize() {
		LOGGER.info("Successfully loaded Sweet Potato Reborn mod! Not the same as Sweet Potato Mod!");
		LOGGER.info("This is for Minecraft 1.20 and above!");
		FabricLoader.getInstance().getEntrypoints("spmreborn", SPRLinkage.class).forEach(SPRLinkage::init);
		RegistryHelper.registerAll();
		
		ComposterHelper.register();
		SPRLootTables.init();
		ReloadListenerRegistry.register(PackType.SERVER_DATA, new MagicalEnchantmentLoader());
		TreeFeatures.init();
		AnimalIngredients.configureParrot();
	}

	public static final Item.Properties defaultProp = new Item.Properties().arch$tab(SPR_ITEMS);

	static {
		// Update to Minecraft 1.20 -- 2023/10/30
		// Deleted util.objsettings.ItemSettings
		// Item
		PEEL = defaultItem("peel", defaultProp);
		BAKED_PURPLE_POTATO = item("baked_purple_potato", new BakedSweetPotatoItem(defaultProp, SweetPotatoType.PURPLE));
		BAKED_RED_POTATO = item("baked_red_potato", new BakedSweetPotatoItem(defaultProp, SweetPotatoType.RED));
		BAKED_WHITE_POTATO = item("baked_white_potato", new BakedSweetPotatoItem(defaultProp, SweetPotatoType.WHITE));
		POTATO_POWDER = defaultItem("potato_powder", defaultProp);
		XMAS_TREATING_BOWL = defaultItem("treating_bowl", new Item.Properties());
		ENCHANTED_PURPLE_POTATO = item("enchanted_purple_potato", new EnchantedSweetPotatoItem(defaultProp.stacksTo(1), SweetPotatoType.PURPLE));
		ENCHANTED_RED_POTATO = item("enchanted_red_potato", new EnchantedSweetPotatoItem(defaultProp.stacksTo(1), SweetPotatoType.RED));
		ENCHANTED_WHITE_POTATO = item("enchanted_white_potato", new EnchantedSweetPotatoItem(defaultProp.stacksTo(1), SweetPotatoType.WHITE));

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

		// Block Items
		PURPLE_POTATO = item("purple_potato", () -> new RawSweetPotatoBlockItem(PURPLE_POTATO_CROP.get(), defaultProp, SweetPotatoType.PURPLE));
		RED_POTATO = item("red_potato", () -> new RawSweetPotatoBlockItem(RED_POTATO_CROP.get(), defaultProp, SweetPotatoType.RED));
		WHITE_POTATO = item("white_potato", () -> new RawSweetPotatoBlockItem(WHITE_POTATO_CROP.get(), defaultProp, SweetPotatoType.WHITE));

		ENCHANTED_WHEAT_SEEDS = AliasedEnchantedItem.of("enchanted_wheat_seeds", ENCHANTED_WHEAT_CROP);
		ENCHANTED_BEETROOT_SEEDS = AliasedEnchantedItem.of("enchanted_beetroot_seeds", ENCHANTED_BEETROOTS_CROP);
		ENCHANTED_VANILLA_POTATO_ITEM = AliasedEnchantedItem.ofMiscFood("enchanted_potato", ENCHANTED_VANILLA_POTATOES_CROP, Foods.POTATO, defaultProp);
		ENCHANTED_CARROT_ITEM = AliasedEnchantedItem.ofMiscFood("enchanted_carrot", ENCHANTED_CARROTS_CROP, Foods.CARROT, defaultProp);
		//ENCHANTED_SUGAR_CANE_ITEM = AliasedEnchantedItem.of("enchanted_sugar_cane", ENCHANTED_SUGAR_CANE, ItemGroup.decorations());
		ENCHANTED_SUGAR_CANE_ITEM = EnchantedBlockItem.registerItem("enchanted_sugar_cane", ENCHANTED_SUGAR_CANE, defaultProp);

		ENCHANTED_ACACIA_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_acacia_leaves", ENCHANTED_ACACIA_LEAVES, defaultProp);
		ENCHANTED_BIRCH_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_birch_leaves", ENCHANTED_BIRCH_LEAVES, defaultProp);
		ENCHANTED_DARK_OAK_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_dark_oak_leaves", ENCHANTED_DARK_OAK_LEAVES, defaultProp);
		ENCHANTED_JUNGLE_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_jungle_leaves", ENCHANTED_JUNGLE_LEAVES, defaultProp);
		ENCHANTED_OAK_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_oak_leaves", ENCHANTED_OAK_LEAVES, defaultProp);
		ENCHANTED_SPRUCE_LEAVES_ITEM = EnchantedBlockItem.registerItem("enchanted_spruce_leaves", ENCHANTED_SPRUCE_LEAVES, defaultProp);

		// Functional Blocks' Items
		MAGIC_CUBE_ITEM = blockItem("magic_cube", MAGIC_CUBE, defaultProp);
		GRINDER_ITEM = blockItem("grinder", GRINDER, defaultProp);
		SEED_UPDATER_ITEM = blockItem("agroforestry_table", SEED_UPDATER, defaultProp);
		ENCHANTED_OAK_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_oak_sapling", ENCHANTED_OAK_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));
		ENCHANTED_SPRUCE_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_spruce_sapling", ENCHANTED_SPRUCE_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));
		ENCHANTED_BIRCH_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_birch_sapling", ENCHANTED_BIRCH_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));
		ENCHANTED_JUNGLE_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_jungle_sapling", ENCHANTED_JUNGLE_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));
		ENCHANTED_ACACIA_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_acacia_sapling", ENCHANTED_ACACIA_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));
		ENCHANTED_DARK_OAK_SAPLING_ITEM = EnchantedBlockItem.registerItem("enchanted_dark_oak_sapling", ENCHANTED_DARK_OAK_SAPLING, defaultProp.rarity(Rarity.UNCOMMON));

		// Screen Handler
		SEED_UPDATER_SCREEN_HANDLER_TYPE = simpleMenuType("seed_updater", SeedUpdaterScreenHandler::new);
		GRINDER_SCREEN_HANDLER_TYPE = simpleMenuType("grinder", GrinderScreenHandler::new);
		MAGIC_CUBE_SCREEN_HANDLER_TYPE = extendedMenuType("magic_cube", MagicCubeScreenHandler::new);

		// Recipe Serializer
		SEED_UPDATING_RECIPE_SERIALIZER = recipeSerializer("seed_updating", SeedUpdatingRecipe.Serializer::new);

		// Recipe Type
		SEED_UPDATING_RECIPE_TYPE = recipeType("seed_updating");

		// Block Entity
		GRINDER_BLOCK_ENTITY_TYPE = blockEntity("grinder", GrinderBlockEntity::new, GRINDER.getOrNull());
		MAGIC_CUBE_BLOCK_ENTITY_TYPE = blockEntity("magic_cube", MagicCubeBlockEntity::new, MAGIC_CUBE.getOrNull());

		// Item Tags
		RAW_SWEET_POTATOES = itemTag("raw_sweet_potatoes");
		ENCHANTED_SWEET_POTATOES = itemTag("enchanted_sweet_potatoes");
		ALL_SWEET_POTATOES = itemTag("sweet_potatoes");
		// About pig food, parrot food and chicken food
		PIG_BREEDING_INGREDIENTS = itemTag("pig_breeding_ingredients");
		CHICKEN_BREEDING_INGREDIENTS = itemTag("chicken_breeding_ingredients");

		// Sounds
		AGROFORESTRY_TABLE_FINISH = sound("block.agroforestry_table.finish");
		GRINDER_GRIND = sound("block.grinder.grind");
		MAGIC_CUBE_ACTIVATE = sound("block.magic_cube.activate");
		MAGIC_CUBE_DEACTIVATE = sound("block.magic_cube.deactivate");
		MAGIC_CUBE_AMBIENT = sound("block.magic_cube.ambient");

		// Stats
		INTERACT_WITH_GRINDER = stat("interact_with_grinder");
		INTERACT_WITH_AGRO = stat("interact_with_agroforestry_table");
		CROP_UPGRADED = stat("crop_upgraded");
		SWEET_POTATO_EATEN = stat("sweet_potato_eaten");
		INTERACT_WITH_MAGIC_CUBE = stat("interact_with_magic_cube");

		// Loot Functions
		SET_ENCHANTED_POTATO_EFFECT = lootFunction("set_enchanted_potato_effect", new SetEnchantedPotatoEffectFunction.Serializer());
	}
}
