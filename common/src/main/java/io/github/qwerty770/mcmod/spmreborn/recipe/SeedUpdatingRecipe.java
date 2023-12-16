package io.github.qwerty770.mcmod.spmreborn.recipe;

import com.google.gson.JsonObject;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public record SeedUpdatingRecipe(ResourceLocation id, Ingredient base,
                                 Ingredient addition,
                                 ItemStack result) implements Recipe<Container> {

    @Override
    public boolean matches(@NotNull Container inv, Level world) {
        return this.base.test(inv.getItem(0)) && this.addition.test(inv.getItem(1));
    }

    @Override
    public @NotNull ItemStack assemble(Container inv, RegistryAccess registryAccess) {
        ItemStack itemStack = this.result.copy();
        CompoundTag compoundTag = inv.getItem(0).getTag();
        if (compoundTag != null) {
            itemStack.setTag(compoundTag.copy());
        }
        return itemStack;
    }

    @Environment(EnvType.CLIENT) @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Environment(EnvType.CLIENT)
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SPRMain.SEED_UPDATER);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SPRMain.SEED_UPDATING_RECIPE_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SPRMain.SEED_UPDATING_RECIPE_TYPE;
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
        @Override
        public @NotNull SeedUpdatingRecipe fromJson(ResourceLocation identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return new SeedUpdatingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public @NotNull SeedUpdatingRecipe fromNetwork(ResourceLocation identifier, FriendlyByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromNetwork(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItem();
            return new SeedUpdatingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, @NotNull SeedUpdatingRecipe recipe) {
            recipe.base.toNetwork(buf);
            recipe.addition.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}