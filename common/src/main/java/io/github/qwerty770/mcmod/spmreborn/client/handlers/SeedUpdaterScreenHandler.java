package io.github.qwerty770.mcmod.spmreborn.client.handlers;

import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.client.SweetPotatoMenuTypes;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipe;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipeInput;
import io.github.qwerty770.mcmod.spmreborn.recipe.SweetPotatoRecipes;
import io.github.qwerty770.mcmod.spmreborn.sound.SweetPotatoSoundEvents;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;

public class SeedUpdaterScreenHandler extends ItemCombinerMenu {
    // see net.minecraft.world.inventory.SmithingMenu
    private final Level level;
    @Nullable
    private RecipeHolder<SeedUpdatingRecipe> selectedRecipe;
    private final List<RecipeHolder<SeedUpdatingRecipe>> recipes;

    public SeedUpdaterScreenHandler(int syncId, Inventory inventory) {
        this(syncId, inventory, ContainerLevelAccess.NULL);
    }

    public SeedUpdaterScreenHandler(int syncId, Inventory inventory, ContainerLevelAccess context) {
        super(SweetPotatoMenuTypes.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(), syncId, inventory, context);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get());
    }

    @Override
    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, (itemStack) -> this.recipes.stream().anyMatch(
                        (recipeHolder) -> recipeHolder.value().isBaseIngredient(itemStack)))
                .withSlot(1, 76, 47, (itemStack) -> this.recipes.stream().anyMatch(
                        (recipeHolder) -> recipeHolder.value().isAdditionIngredient(itemStack)))
                .withResultSlot(2, 134, 47).build();
    }

    protected boolean isValidBlock(@NotNull BlockState state) {
        return state.is(SweetPotatoBlocks.SEED_UPDATER.get());
    }

    @Override
    protected boolean mayPickup(Player player, boolean present) {
        return this.selectedRecipe != null && this.selectedRecipe.value().matches(createRecipeInput(), this.level);
    }

    @Override
    protected void onTake(Player player, @NotNull ItemStack stack) {
        this.resultSlots.awardUsedRecipes(player, Collections.singletonList(stack));
        this.putStack(0);
        this.putStack(1);
        this.access.execute((world1, blockPos) -> world1.playSound(
                null, blockPos, SweetPotatoSoundEvents.AGROFORESTRY_TABLE_FINISH.get(), SoundSource.BLOCKS, 1.0F, world1.getRandom().nextFloat() * 0.1F + 0.9F));
        player.awardStat(SweetPotatoStats.CROP_UPGRADED);
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, SweetPotatoBlocks.SEED_UPDATER.get());
    }

    private SeedUpdatingRecipeInput createRecipeInput() {
        return new SeedUpdatingRecipeInput(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    private void putStack(int i) {
        ItemStack itemStack = this.inputSlots.getItem(i);
        itemStack.shrink(1);
        this.inputSlots.setItem(i, itemStack);
    }

    @Override
    public void createResult() {
        SeedUpdatingRecipeInput input = createRecipeInput();
        List<RecipeHolder<SeedUpdatingRecipe>> list1 = this.level.getRecipeManager().getRecipesFor(
                SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get(), input, this.level);
        if (list1.isEmpty())
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        else {
            RecipeHolder<SeedUpdatingRecipe> recipeHolder = list1.get(0);
            ItemStack itemStack = recipeHolder.value().assemble(input, this.level.registryAccess());
            if (itemStack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = recipeHolder;
                this.resultSlots.setRecipeUsed(recipeHolder);
                this.resultSlots.setItem(0, itemStack);
            }
        }
    }

    public int getSlotToQuickMoveTo(ItemStack stack) {
        return this.findSlotToQuickMoveTo(stack).orElse(0);
    }

    private static OptionalInt findSlotMatchingIngredient(SeedUpdatingRecipe recipe, ItemStack stack) {
        if (recipe.isBaseIngredient(stack)) {
            return OptionalInt.of(0);
        } else {
            return recipe.isAdditionIngredient(stack) ? OptionalInt.of(1) : OptionalInt.empty();
        }
    }

    @Override
    protected boolean canMoveIntoInputSlots(ItemStack itemStack) {
        // ItemCombinerMenu.shouldQuickMoveToAdditionalSlot(ItemStack itemStack) in minecraft 1.19-
        return findSlotToQuickMoveTo(itemStack).isPresent();
    }

    private OptionalInt findSlotToQuickMoveTo(ItemStack itemStack) {
        return this.recipes.stream().flatMapToInt((recipeHolder) ->
                        findSlotMatchingIngredient(recipeHolder.value(), itemStack).stream())
                .filter((i) -> !this.getSlot(i).hasItem())
                .findFirst();
    }
}
