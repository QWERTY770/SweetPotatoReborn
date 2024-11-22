package io.github.qwerty770.mcmod.spmreborn.items;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

@StableApi
public class AliasedEnchantedItem extends BlockItem {
    // Update to Minecraft 1.20 -- 2023/10/30  Removed all usages of net.fabricmc.fabric.api.item.v1.FabricItemSettings
    public AliasedEnchantedItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    // Update to Minecraft 1.20 -- 2023/12/16  RegistrySupplier
    @ApiStatus.Internal
    public static RegistrySupplier<Item> of(String id, RegistrySupplier<Block> original, Properties properties) {
        return RegistryHelper.item(id, (p) -> new AliasedEnchantedItem(original.get(), p), properties);
    }

    @ApiStatus.Internal
    public static RegistrySupplier<Item> ofFood(String id, RegistrySupplier<Block> original, FoodProperties foodComponent, Properties properties) {
        return RegistryHelper.item(id, (p) -> new AliasedEnchantedItem(original.get(), p), properties.food(foodComponent));
    }
}
