package io.github.qwerty770.mcmod.spmreborn;

import dev.architectury.registry.ReloadListenerRegistry;
import io.github.qwerty770.mcmod.spmreborn.blocks.*;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.SweetPotatoBlockEntityTypes;
import io.github.qwerty770.mcmod.spmreborn.items.*;
import io.github.qwerty770.mcmod.spmreborn.loot.SPRLootTables;
import io.github.qwerty770.mcmod.spmreborn.loot.SweetPotatoLootFunctions;
import io.github.qwerty770.mcmod.spmreborn.magic.MagicalEnchantmentLoader;
import io.github.qwerty770.mcmod.spmreborn.recipe.SweetPotatoRecipes;
import io.github.qwerty770.mcmod.spmreborn.client.SweetPotatoMenuTypes;
import io.github.qwerty770.mcmod.spmreborn.sound.SweetPotatoSoundEvents;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.registries.AnimalIngredients;
import io.github.qwerty770.mcmod.spmreborn.util.registries.ComposterHelper;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagContainer;
import io.github.qwerty770.mcmod.spmreborn.world.gen.tree.*;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.*;

@StableApi
public class SPRMain {
	private SPRMain() { }
	private static final Logger LOGGER = LoggerFactory.getLogger("Sweet Potato Reborn");
	public static final String MODID = "spmreborn";

	public static Logger getLogger() {
		return LOGGER;
	}

	public static void register(){
		SweetPotatoBlocks.LOG_WRAPPER.run(); blockRegistry.register();
		SweetPotatoBlockEntityTypes.LOG_WRAPPER.run(); blockEntityRegistry.register();
		SweetPotatoItems.LOG_WRAPPER.run(); itemRegistry.register();
		SweetPotatoRecipes.LOG_WRAPPER.run(); recipeTypeRegistry.register(); recipeSerializerRegistry.register();
		SweetPotatoLootFunctions.LOG_WRAPPER.run(); lootFunctionRegistry.register();
		SweetPotatoStats.LOG_WRAPPER.run(); statRegistry.register();
		SweetPotatoSoundEvents.LOG_WRAPPER.run(); soundRegistry.register();
		SweetPotatoMenuTypes.LOG_WRAPPER.run(); menuRegistry.register();
		SweetPotatoTreeFeatures.LOG_WRAPPER.run(); //featureRegistry.register();
	}

	public static void init() {
		SPRMain.register();
		// TODO: Is it necessary?
		// FabricLoader.getInstance().getEntrypoints(MODID, SPRLinkage.class).forEach(SPRLinkage::init);
		ReloadListenerRegistry.register(PackType.SERVER_DATA, new MagicalEnchantmentLoader());
		ComposterHelper.register();
		SPRLootTables.init();
		AnimalIngredients.configureParrot();
		LOGGER.info("Successfully loaded Sweet Potato Reborn mod! Not the same as Sweet Potato Mod!");
		LOGGER.info("This is for Minecraft 1.20 and above!");
	}

	// Item Tags
	public static final TagContainer<Item> RAW_SWEET_POTATOES;
	public static final TagContainer<Item> ENCHANTED_SWEET_POTATOES;
	public static final TagContainer<Item> ALL_SWEET_POTATOES;
	// About Pigs & Parrots
	public static final TagContainer<Item> PIG_BREEDING_INGREDIENTS;
	public static final TagContainer<Item> CHICKEN_BREEDING_INGREDIENTS;

	static {
		// Item Tags
		RAW_SWEET_POTATOES = itemTag("raw_sweet_potatoes");
		ENCHANTED_SWEET_POTATOES = itemTag("enchanted_sweet_potatoes");
		ALL_SWEET_POTATOES = itemTag("sweet_potatoes");
		// About pig food, parrot food and chicken food
		PIG_BREEDING_INGREDIENTS = itemTag("pig_breeding_ingredients");
		CHICKEN_BREEDING_INGREDIENTS = itemTag("chicken_breeding_ingredients");
	}
}
