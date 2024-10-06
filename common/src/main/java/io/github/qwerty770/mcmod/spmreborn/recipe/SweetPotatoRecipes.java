package io.github.qwerty770.mcmod.spmreborn.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.recipeSerializer;
import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.recipeType;

public class SweetPotatoRecipes {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("recipes");
    // Recipe Serializer
    public static final RegistrySupplier<RecipeSerializer<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_SERIALIZER =
            recipeSerializer("seed_updating", SeedUpdatingRecipe.Serializer::new);
    // Recipe Type
    public static final RegistrySupplier<RecipeType<SeedUpdatingRecipe>> SEED_UPDATING_RECIPE_TYPE =
            recipeType("seed_updating");
}
