package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

@StableApi
public class AliasedEnchantedItem extends ItemNameBlockItem {
    // Update to Minecraft 1.20 -- 2023/10/30
    // Removed all usages of net.fabricmc.fabric.api.item.v1.FabricItemSettings
    public AliasedEnchantedItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @ApiStatus.Internal
    public static AliasedEnchantedItem of(String id, Block original) {
        return of(id, original, SPRMain.defaultProp);
    }

    @ApiStatus.Internal
    public static AliasedEnchantedItem of(String id, Block original, Properties properties) {
        return (AliasedEnchantedItem) RegistryHelper.item(id, new AliasedEnchantedItem(original, properties));
    }

    @ApiStatus.Internal
    public static AliasedEnchantedItem ofMiscFood(String id, Block original, FoodProperties foodComponent, Properties properties) {
        return (AliasedEnchantedItem) RegistryHelper.item(id, new AliasedEnchantedItem(original, properties.food(foodComponent)));
    }
}
