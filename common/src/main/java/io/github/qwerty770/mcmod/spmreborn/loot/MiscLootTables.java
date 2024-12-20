package io.github.qwerty770.mcmod.spmreborn.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class MiscLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static LootPool.Builder pillagerOutpost() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(NestedLootTable.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(4));
        return pool;
    }

    public static LootPool.Builder shipwreckSupply() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(NestedLootTable.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(7));
        return pool;
    }

    public static LootPool.Builder woodlandMansion() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(NestedLootTable.lootTableReference(SPRLootTables.MORE_RAW_SWEET_POTATOES).setWeight(15));
        return pool;
    }
}
