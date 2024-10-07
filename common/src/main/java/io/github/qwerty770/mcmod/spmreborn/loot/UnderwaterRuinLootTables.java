package io.github.qwerty770.mcmod.spmreborn.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

import static io.github.qwerty770.mcmod.spmreborn.loot.SPRLootTables.MORE_RAW_SWEET_POTATOES;

public class UnderwaterRuinLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static LootPool.Builder big() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(NestedLootTable.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(10));
        return pool;
    }

    public static LootPool.Builder small() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(NestedLootTable.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(5));
        return pool;
    }
}
