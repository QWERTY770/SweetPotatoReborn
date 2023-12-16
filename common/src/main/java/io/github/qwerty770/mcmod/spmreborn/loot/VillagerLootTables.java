package io.github.qwerty770.mcmod.spmreborn.loot;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class VillagerLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    private static final NumberProvider universalPotatoRange = UniformGenerator.between(1.0F, 7.0F);

    public static LootPool.Builder plains() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(LootItem.lootTableItem(Items.POTATO).setWeight(6).apply(SetItemCountFunction.setCount(universalPotatoRange)));
        pool.add(LootTableReference.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(6));
        return pool;
        // LootUtils.setEntry(pools, 0, 3, LootItem.lootTableItem(Items.POTATO).setWeight(6).apply(
        //         SetItemCountFunction.setCount(universalPotatoRange)));
        // LootUtils.addEntry(pools, 0, LootTableReference.lootTableReference(SPMLootTables.MORE_RAW_SWEET_POTATOES).setWeight(6));

    }

    public static LootPool.Builder snowy() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(LootItem.lootTableItem(Items.POTATO).setWeight(7).apply(SetItemCountFunction.setCount(universalPotatoRange)));
        pool.add(LootItem.lootTableItem(Items.BREAD).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))));
        pool.add(LootTableReference.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(7));
        return pool;
        // LootUtils.setEntry(pools, 0, 2,
        //         LootItem.lootTableItem(Items.POTATO).setWeight(7).apply(
        //                 SetItemCountFunction.setCount(universalPotatoRange)));
        // LootUtils.setEntry(pools, 0, 3,
        //         LootItem.lootTableItem(Items.BREAD).setWeight(7).apply(
        //                 SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))));
        // LootUtils.addEntry(pools, 0,
        //         LootTableReference.lootTableReference(SPMLootTables.MORE_RAW_SWEET_POTATOES).setWeight(7) );

    }

    public static LootPool.Builder taiga() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(LootItem.lootTableItem(Items.POTATO).setWeight(7).apply(SetItemCountFunction.setCount(universalPotatoRange)));
        pool.add(LootTableReference.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(7));
        return pool;
        // LootUtils.setEntry(pools, 0, 3,
        //         LootItem.lootTableItem(Items.POTATO).setWeight(7).apply(
        //                 SetItemCountFunction.setCount(universalPotatoRange)));
        // LootUtils.addEntry(pools, 0,
        //         LootTableReference.lootTableReference(SPMLootTables.MORE_RAW_SWEET_POTATOES).setWeight(7));

    }
}
