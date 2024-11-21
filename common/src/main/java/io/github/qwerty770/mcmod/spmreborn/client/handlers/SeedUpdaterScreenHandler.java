package io.github.qwerty770.mcmod.spmreborn.client.handlers;

import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.client.SweetPotatoMenuTypes;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipe;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipeInput;
import io.github.qwerty770.mcmod.spmreborn.recipe.SweetPotatoRecipes;
import io.github.qwerty770.mcmod.spmreborn.sound.SweetPotatoSoundEvents;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SeedUpdaterScreenHandler extends ItemCombinerMenu {
    // see net.minecraft.world.inventory.SmithingMenu
    private final Level level;
    private List<Ingredient> baseItems;
    private List<Ingredient> additionItems;
    private final DataSlot hasRecipeError;

    public SeedUpdaterScreenHandler(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public SeedUpdaterScreenHandler(int containerId, Inventory inventory, ContainerLevelAccess access) {
        this(containerId, inventory, access, inventory.player.level());
    }

    public SeedUpdaterScreenHandler(int containerId, Inventory inventory, ContainerLevelAccess access, Level level) {
        super(SweetPotatoMenuTypes.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(),
                containerId, inventory, access, createInputSlotDefinitions());
        this.hasRecipeError = DataSlot.standalone();
        this.level = level;
        this.baseItems = List.of();
        this.additionItems = List.of();
        if (this.level instanceof ServerLevel serverLevel){
            this.baseItems = serverLevel.recipeAccess().getRecipes().stream()
                    .map(RecipeHolder::value)
                    .filter((recipe -> recipe instanceof SeedUpdatingRecipe))
                    .map((recipe -> (SeedUpdatingRecipe) recipe))
                    .map(SeedUpdatingRecipe::base).toList();
            this.additionItems = serverLevel.recipeAccess().getRecipes().stream()
                    .map(RecipeHolder::value)
                    .filter((recipe -> recipe instanceof SeedUpdatingRecipe))
                    .map((recipe -> (SeedUpdatingRecipe) recipe))
                    .map(SeedUpdatingRecipe::addition).toList();
        }
        this.addDataSlot(this.hasRecipeError).set(0);
    }

    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, (itemStack) -> true)
                .withSlot(1, 76, 47, (itemStack) -> true)
                .withResultSlot(2, 134, 47).build();
    }

    private SeedUpdatingRecipeInput createRecipeInput() {
        return new SeedUpdatingRecipeInput(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    protected boolean isValidBlock(@NotNull BlockState state) {
        return state.is(SweetPotatoBlocks.SEED_UPDATER.get());
    }

    @Override
    protected void onTake(Player player, @NotNull ItemStack stack) {
        this.resultSlots.awardUsedRecipes(player, Collections.singletonList(stack));
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((world1, blockPos) -> world1.playSound(
                null, blockPos, SweetPotatoSoundEvents.AGROFORESTRY_TABLE_FINISH.get(), SoundSource.BLOCKS, 1.0F, world1.getRandom().nextFloat() * 0.1F + 0.9F));
        player.awardStat(SweetPotatoStats.CROP_UPGRADED);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, SweetPotatoBlocks.SEED_UPDATER.get());
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemStack = this.inputSlots.getItem(index);
        if (!itemStack.isEmpty()) {
            itemStack.shrink(1);
            this.inputSlots.setItem(index, itemStack);
        }
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (this.level instanceof ServerLevel) {
            boolean flag = this.getSlot(0).hasItem() && this.getSlot(1).hasItem() && !this.getSlot(this.getResultSlot()).hasItem();
            this.hasRecipeError.set(flag ? 1 : 0);
        }
    }

    @Override
    public void createResult() {
        SeedUpdatingRecipeInput input = createRecipeInput();
        Optional<RecipeHolder<SeedUpdatingRecipe>> recipeHolder;
        if (this.level instanceof ServerLevel serverLevel) {
            recipeHolder = serverLevel.recipeAccess().getRecipeFor(SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get(),
                    input, serverLevel);
        } else {
            recipeHolder = Optional.empty();
        }
        recipeHolder.ifPresentOrElse((holder) -> {
            ItemStack itemStack = holder.value().assemble(input, this.level.registryAccess());
            if (itemStack.isItemEnabled(this.level.enabledFeatures())) {
                this.resultSlots.setRecipeUsed(holder);
                this.resultSlots.setItem(0, itemStack);
            }
        }, () -> this.resultSlots.setItem(0, ItemStack.EMPTY));
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public boolean canMoveIntoInputSlots(ItemStack stack) {
        if (this.baseItems.stream().anyMatch((ingredient -> ingredient.test(stack))) && !this.getSlot(0).hasItem()) {
            return true;
        } else {
            return this.additionItems.stream().anyMatch((ingredient -> ingredient.test(stack))) && !this.getSlot(1).hasItem();
        }
    }
}
