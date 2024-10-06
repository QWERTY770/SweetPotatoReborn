package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@StableApi
public class EnchantedItem extends Item {
    public EnchantedItem(Properties settings) {
        super(settings);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
