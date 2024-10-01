package io.github.qwerty770.mcmod.spmreborn.client.handlers;

import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.client.SweetPotatoMenuTypes;
import io.github.qwerty770.mcmod.spmreborn.recipe.SeedUpdatingRecipe;
import io.github.qwerty770.mcmod.spmreborn.recipe.SweetPotatoRecipes;
import io.github.qwerty770.mcmod.spmreborn.sound.SweetPotatoSoundEvents;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class SeedUpdaterScreenHandler extends ItemCombinerMenu {
    private final Level world;
    @Nullable
    private SeedUpdatingRecipe recipe;
    private final List<SeedUpdatingRecipe> list;

    public SeedUpdaterScreenHandler(int syncId, Inventory inventory) {
        this(syncId, inventory, ContainerLevelAccess.NULL);
    }

    public SeedUpdaterScreenHandler(int syncId, Inventory inventory, ContainerLevelAccess context) {
        super(SweetPotatoMenuTypes.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(), syncId, inventory, context);
        this.world = inventory.player.level();
        this.list = this.world.getRecipeManager().getAllRecipesFor(SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get());
    }

    protected boolean isValidBlock(@NotNull BlockState state) {
        return state.is(SweetPotatoBlocks.SEED_UPDATER.get());
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, SweetPotatoBlocks.SEED_UPDATER.get());
    }

    @Override
    public void createResult() {
        List<SeedUpdatingRecipe> list1 = this.world.getRecipeManager().getRecipesFor(
                SweetPotatoRecipes.SEED_UPDATING_RECIPE_TYPE.get(), this.inputSlots, this.world
        );
        if (list1.isEmpty())
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        else {
            this.recipe = list1.get(0);
            ItemStack itemStack = this.recipe.assemble(this.inputSlots, this.world.registryAccess());
            this.resultSlots.setRecipeUsed(recipe);
            this.resultSlots.setItem(0, itemStack);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return null;
    }

    @Override
    protected boolean mayPickup(Player player, boolean present) {
        return this.recipe != null && this.recipe.matches(this.inputSlots, this.world);
    }

    @Override
    protected void onTake(Player player, @NotNull ItemStack stack) {
        //stack.onCraft(player.world, player, stack.getCount());
        this.resultSlots.awardUsedRecipes(player, Collections.singletonList(stack));
        this.putStack(0);
        this.putStack(1);
        //output.markDirty();
        this.access.execute((world1, blockPos) -> {
            //world1.syncWorldEvent(1044, blockPos, 8844110));
            world1.playSound(null, blockPos, SweetPotatoSoundEvents.AGROFORESTRY_TABLE_FINISH.get(), SoundSource.BLOCKS, 1.0F, world1.getRandom().nextFloat() * 0.1F + 0.9F);
        });
        player.awardStat(SweetPotatoStats.CROP_UPGRADED);
        //return stack;
    }

    private void putStack(int i) {
        ItemStack itemStack = this.inputSlots.getItem(i);
        itemStack.shrink(1);
        this.inputSlots.setItem(i, itemStack);
    }

    // TODO
    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack itemStack) {
        return this.list.stream().anyMatch(seedUpdatingRecipe ->
                seedUpdatingRecipe.isAdditionIngredient(itemStack));
    }

    @Override
    public void slotsChanged(Container inventory) {
        super.slotsChanged(inventory);
    }
}
