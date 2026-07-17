package io.github.qwerty770.mcmod.spmreborn.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public record SeedUpdatingRecipe(Ingredient base, Ingredient addition, ItemStackTemplate result) implements Recipe<SeedUpdatingRecipeInput> {
    // See net.minecraft.world.item.crafting.SmithingTransformRecipe.
    @Override
    public boolean matches(SeedUpdatingRecipeInput input, Level level) {
        return this.base.test(input.base()) && this.addition.test(input.addition());
    }

    @Override
    public @NotNull ItemStack assemble(SeedUpdatingRecipeInput input) {
        return TransmuteRecipe.createWithOriginalComponents(this.result, input.base());
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<SeedUpdatingRecipeInput>> getSerializer() {
        return SweetPotatoRecipes.SEED_UPDATING_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<? extends Recipe<SeedUpdatingRecipeInput>> getType() {
        return SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public @NotNull String group() {
        return "";
    }

    @Override
    public @NotNull PlacementInfo placementInfo(){
        return PlacementInfo.create(List.of(this.base(), this.addition()));
    }

    @Override
    public @NotNull List<RecipeDisplay> display() {
        return List.of(new SeedUpdatingRecipeDisplay(Ingredient.optionalIngredientToDisplay(Optional.of(this.base)),
                Ingredient.optionalIngredientToDisplay(Optional.of(this.addition)),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(SweetPotatoItems.SEED_UPDATER_ITEM.get())));
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory(){
        return SweetPotatoRecipes.SEED_UPDATING_CATEGORY.get();
    }

    public static final MapCodec<SeedUpdatingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Ingredient.CODEC.fieldOf("base").forGetter((arg) -> arg.base),
                    Ingredient.CODEC.fieldOf("addition").forGetter((arg) -> arg.addition),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter((arg) -> arg.result))
                    .apply(instance, SeedUpdatingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SeedUpdatingRecipe> STREAM_CODEC = StreamCodec.of(SeedUpdatingRecipe::toNetwork, SeedUpdatingRecipe::fromNetwork);

    private static SeedUpdatingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
        Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
        ItemStackTemplate itemStack = ItemStackTemplate.STREAM_CODEC.decode(buffer);
        return new SeedUpdatingRecipe(ingredient1, ingredient2, itemStack);
    }

    private static void toNetwork(RegistryFriendlyByteBuf buffer, SeedUpdatingRecipe recipe) {
        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
        ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result);
    }
}
