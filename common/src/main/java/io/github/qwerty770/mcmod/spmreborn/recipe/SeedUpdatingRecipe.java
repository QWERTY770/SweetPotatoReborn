package io.github.qwerty770.mcmod.spmreborn.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public record SeedUpdatingRecipe(Ingredient base, Ingredient addition, ItemStack result) implements Recipe<SeedUpdatingRecipeInput> {

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

    @Environment(EnvType.CLIENT)
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SweetPotatoBlocks.SEED_UPDATER.get());
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SweetPotatoRecipes.SEED_UPDATING_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get();
    }

    public boolean isBaseIngredient(ItemStack itemStack) {
        return this.base.test(itemStack);
    }

    public boolean isAdditionIngredient(ItemStack itemStack) {
        return this.addition.test(itemStack);
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY,
                this.base(), this.addition());
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