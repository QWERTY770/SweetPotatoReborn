package io.github.qwerty770.mcmod.spmreborn.blocks.entities;

import io.github.qwerty770.mcmod.spmreborn.blocks.GrinderBlock;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import io.github.qwerty770.mcmod.spmreborn.lib.blockentity.AbstractLockableContainerBlockEntity;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.GrinderScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.util.world.BooleanStateManager;
import io.github.qwerty770.mcmod.spmreborn.util.iprops.IntGrinderProperties;
import io.github.qwerty770.mcmod.spmreborn.util.registries.GrindingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GrinderBlockEntity extends AbstractLockableContainerBlockEntity implements WorldlyContainer {
    private int grindTime;
    private int grindTimeTotal;
    private double ingredientData;

    private byte absorbCooldown;
    private static final byte MAX_COOLDOWN = 5;

    public final IntGrinderProperties properties;

    protected final BooleanStateManager stateHelper;

    public GrinderBlockEntity(BlockPos pos, BlockState state) {
        this(SweetPotatoBlockEntityTypes.GRINDER_BLOCK_ENTITY_TYPE.get(), pos, state);
    }

    protected GrinderBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state, 2);
        // this.inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

        this.properties = new IntGrinderProperties() {
            @Override
            public int getGrindTime() {
                return grindTime;
            }

            @Override
            public int getGrindTimeTotal() {
                return grindTimeTotal;
            }

            @Override
            public double getIngredientData() {
                return ingredientData;
            }

            @Override
            public void setGrindTime(int grindTime) {
                GrinderBlockEntity.this.grindTime = grindTime;
            }

            @Override
            public void setGrindTimeTotal(int grindTimeTotal) {
                GrinderBlockEntity.this.grindTimeTotal = grindTimeTotal;
            }

            @Override
            public void setIngredientData(double ingredientData) {
                GrinderBlockEntity.this.ingredientData = ingredientData;
            }
        };

        this.absorbCooldown = -1;
        this.grindTime = -1;
        this.ingredientData = 0.0D;

        this.stateHelper = new BooleanStateManager(GrinderBlock.GRINDING) {
            public boolean shouldChange(boolean newOne) {
                assert GrinderBlockEntity.this.level != null;
                return GrinderBlockEntity.this.level.getBlockState(pos).getValue(property) != newOne;
            }

            @Override
            public void run() {
                assert GrinderBlockEntity.this.level != null;
                boolean b;
                if (this.shouldChange(b = GrinderBlockEntity.this.isGrinding()))
                    GrinderBlockEntity.this.level.setBlockAndUpdate(
                            pos, GrinderBlockEntity.this.level.getBlockState(pos).setValue(property, b)
                    );
                GrinderBlockEntity.this.level.levelEvent(1132119, GrinderBlockEntity.this.worldPosition, 805);
            }
        };
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.grindTime = tag.getShort("GrindTime");
        this.grindTimeTotal = tag.getShort("GrindTimeTotal");
        //this.propertyDelegate.set(2 /*IngredientData*/, tag.getInt("IngredientData"));
        this.ingredientData = tag.getDouble("IngredientData");
        this.absorbCooldown = tag.getByte("absorbCooldown");

    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putShort("GrindTime", (short) grindTime);
        tag.putShort("GrindTimeTotal", (short) grindTimeTotal);
        //Inventories.writeNbt(tag, this.inventory);
        tag.putDouble("IngredientData", ingredientData);
        tag.putByte("absorbCooldown", absorbCooldown);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.spmreborn.grinding");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return new GrinderScreenHandler(syncId, playerInventory, Objects.requireNonNull(this.getLevel()), this, this.properties);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
    }

    @Override
    public void tick(@NotNull Level world, BlockPos pos, BlockState state) {
        boolean shallMarkDirty = false;

        if (!world.isClientSide) {
            // Grind Process
            if (this.grindTime >= this.grindTimeTotal && this.grindTimeTotal != 0 && this.canAcceptRecipeOutput()) { // 200+, 200, yesOutput
                // Output
                this.grindTime = -1;
                this.grindTimeTotal = 0; // this.getGrindTime()
                this.craftRecipe();
                shallMarkDirty = true;
            } else if (this.grindTime >= 0 && this.grindTime < this.grindTimeTotal && this.canAcceptRecipeOutput() /* && this.grindTime not enough*/) {
                // Grind
                ++this.grindTime;
                shallMarkDirty = true;
            } else if (!this.canAcceptRecipeOutput() && this.isGrinding()) {
                // RollBack & Stuck
                // Still grinding, but not continuing
                this.grindTime = net.minecraft.util.Mth.clamp(this.grindTime - 2, 0, grindTimeTotal);
                shallMarkDirty = true;
            }

            // Cool down
            if (!this.shallCooldown()) {
                // New round tern
                if (GrindingUtils.grindable(this.inventory.get(0))) {
                    // Absorb
                    //this.ingredientData += INGREDIENT_DATA_MAP.getDouble(this.inventory.get(0).getItem());
                    this.ingredientData += GrindingUtils.ingredientDataMap().getDouble(this.inventory.get(0).getItem());
                    this.inventory.get(0).shrink(1);
                    this.absorbCooldown = MAX_COOLDOWN;
                    shallMarkDirty = true;
                }
            } else {
                --this.absorbCooldown;
            }

            // Operation of ingredientData check
            if (this.ingredientData >= 15 /*instead of 9*/ && this.grindTime < 0 /*not in grind process*/) {
                this.ingredientData -= 15;
                this.grindTime = 0;
                this.grindTimeTotal = this.getGrindTime();
                shallMarkDirty = true;
            }

            // Check State each 50tick
            if (world.getGameTime() % 50L == 8L)
                stateHelper.run();
        }

        if (shallMarkDirty) setChanged();
    }

    private boolean shallCooldown() {
        return this.absorbCooldown > 0;
    }

    // Update to Minecraft 1.20 -- 2023/06/29
    public boolean notSameItem(ItemStack stack1, ItemStack stack2) {
        return !stack1.is(stack2.getItem());
    }

    //@HardCoded
    private void craftRecipe() {
        this.craftRecipe(new ItemStack(SweetPotatoItems.POTATO_POWDER.get()));
    }

    //@HardCoded
    private void craftRecipe(ItemStack shallOutput) {
        ItemStack invOutput = this.inventory.get(1);

        if (notSameItem(invOutput, shallOutput))
            this.inventory.set(1, shallOutput.copy());
        else if (invOutput.getItem() == SweetPotatoItems.POTATO_POWDER.get())
            invOutput.grow(1);
    }

    protected boolean canAcceptRecipeOutput() {
        return canAcceptRecipeOutput(SweetPotatoItems.POTATO_POWDER.get());
    }

    /**
     * <p>In the past, Grinder should check the first slot
     * (input slot) if it is non-null.<sup>[More info needed]
     * </sup>&nbsp;Now we've found it is a nonsense.
     * <s>Actually, it's a feature.</s></p>
     *
     * <p>Besides, we use the {@link ItemLike} interface
     * instead of inline {@link SweetPotatoItems#POTATO_POWDER} because
     * {@link GrinderBlockEntity} can be changed upside-down
     * at any time. This method can be a great API.</p>
     *
     * @since beta-1.0.0
     */
    protected boolean canAcceptRecipeOutput(ItemLike item) {
        //ItemStack shallBeOutput = new ItemStack(SPMMain.POTATO_POWDER);
        ItemStack outInv = this.inventory.get(1);
        if (outInv.isEmpty())
            return true;
        if (!outInv.is(item.asItem()))
            return false;
        if (outInv.getCount() < this.getMaxStackSize() && outInv.getCount() < outInv.getMaxStackSize())
            return true;
        return outInv.getCount() < item.asItem().getDefaultMaxStackSize();
    }

    private boolean isGrinding() {
        return this.grindTime > 0;
    }

    protected int getGrindTime() {
        return 200;
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction side) {
        return side == Direction.DOWN ? new int[]{1} : new int[]{0};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return slot == 1 && dir == Direction.DOWN;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == 0 && GrindingUtils.grindable(stack);
    }
}
