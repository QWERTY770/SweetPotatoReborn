package io.github.qwerty770.mcmod.spmreborn.util.inventory;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MagicCubeInputSlot extends Slot {
    public MagicCubeInputSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return SPRMain.RAW_SWEET_POTATOES.contains(stack.getItem());
        //return stack.getItem().isIn(SPMMain.RAW_SWEET_POTATOES);
    }
}
