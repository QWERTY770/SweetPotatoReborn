package io.github.qwerty770.mcmod.spmreborn.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.*;

public class SweetPotatoRecipes {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("recipes");
    // Recipe Book Category, for 1.21.2+
    public static final RegistrySupplier<RecipeBookCategory> SEED_UPDATING_CATEGORY =
            recipeBookCategory("seed_updating");
    // Recipe Display, for 1.21.2+
    public static final RegistrySupplier<RecipeDisplay.Type<SeedUpdatingRecipeDisplay>> SEED_UPDATING_DISPLAY =
            recipeDisplay("seed_updating", () -> SeedUpdatingRecipeDisplay.TYPE);
    // Recipe Serializer
    public static final RegistrySupplier<RecipeSerializer<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_SERIALIZER =
            recipeSerializer("seed_updating", SeedUpdatingRecipe.Serializer::new);
    // Recipe Type
    public static final RegistrySupplier<RecipeType<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_TYPE =
            recipeType("seed_updating");
}
