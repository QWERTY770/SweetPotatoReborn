package io.github.qwerty770.mcmod.spmreborn.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public record SeedUpdatingRecipeInput(ItemStack base, ItemStack addition) implements RecipeInput {
    // see net.minecraft.world.item.crafting.SmithingRecipeInput
    @Override
    public ItemStack getItem(int index) {
        ItemStack stack;
        switch (index) {
            case 0 -> stack = this.base;
            case 1 -> stack = this.addition;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        }
        return stack;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.base.isEmpty() && this.addition.isEmpty();
    }

    public ItemStack base() {
        return this.base;
    }

    public ItemStack addition() {
        return this.addition;
    }
}