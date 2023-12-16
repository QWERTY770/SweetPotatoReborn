package io.github.qwerty770.mcmod.spmreborn.mixin.ingredient;

import io.github.qwerty770.mcmod.spmreborn.mixin.acc.IngredientAccessor;
import io.github.qwerty770.mcmod.spmreborn.util.registries.AnimalIngredients;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Pig.class)
@SuppressWarnings("all")
abstract class PigEntityMixin {
    @Shadow @Final @Mutable
    private static Ingredient FOOD_ITEMS;

    static {
        IngredientAccessor acc = (IngredientAccessor) (Object) FOOD_ITEMS;
        acc.setMatchingStacks(null);    // clear cache
        ItemStack[] matchingStacks = FOOD_ITEMS.getItems();
        FOOD_ITEMS = Ingredient.of(AnimalIngredients.configurePig(matchingStacks));
    }
}
