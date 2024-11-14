package io.github.qwerty770.mcmod.spmreborn.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public record SeedUpdatingRecipe(Ingredient base, Ingredient addition, ItemStack result) implements Recipe<SeedUpdatingRecipeInput> {
    // Update to Minecraft 1.21.3 -- 2024/11/15
    // see net.minecraft.world.item.crafting.SmithingTransformRecipe
    @Override
    public boolean matches(SeedUpdatingRecipeInput input, Level level) {
        return this.base.test(input.base()) && this.addition.test(input.addition());
    }

    @Override
    public @NotNull ItemStack assemble(SeedUpdatingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack itemstack = input.base().transmuteCopy(this.result.getItem(), this.result.getCount());
        itemstack.applyComponents(this.result.getComponentsPatch());
        return itemstack;
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

    public boolean isBaseIngredient(ItemStack itemStack) {
        return this.base.test(itemStack);
    }

    public boolean isAdditionIngredient(ItemStack itemStack) {
        return this.addition.test(itemStack);
    }

    public static class Serializer implements RecipeSerializer<SeedUpdatingRecipe> {
        private static final MapCodec<SeedUpdatingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(Ingredient.CODEC.fieldOf("base").forGetter((arg) -> arg.base),
                        Ingredient.CODEC.fieldOf("addition").forGetter((arg) -> arg.addition),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter((arg) -> arg.result))
                        .apply(instance, SeedUpdatingRecipe::new));
        private static final StreamCodec<RegistryFriendlyByteBuf, SeedUpdatingRecipe> STREAM_CODEC = StreamCodec.of(SeedUpdatingRecipe.Serializer::toNetwork, SeedUpdatingRecipe.Serializer::fromNetwork);

        private static SeedUpdatingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack itemStack = ItemStack.STREAM_CODEC.decode(buffer);
            return new SeedUpdatingRecipe(ingredient1, ingredient2, itemStack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, @NotNull SeedUpdatingRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

        @Override
        public @NotNull MapCodec<SeedUpdatingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SeedUpdatingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}