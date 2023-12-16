package io.github.qwerty770.mcmod.spmreborn.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;

import static io.github.qwerty770.mcmod.spmreborn.loot.SPRLootTables.MORE_RAW_SWEET_POTATOES;

public class StrongholdLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static LootPool.Builder corridor() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(LootTableReference.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(11));
        return pool;
        // LootUtils.addEntry(((LootTableBuilderAccessor) hooks).getPools(), 0,
        //         LootTableReference.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(11));
    }

    public static LootPool.Builder crossing() {
        LootPool.Builder pool = LootPool.lootPool();
        pool.add(LootTableReference.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(12));
        return pool;
        // LootUtils.addEntry(((LootTableBuilderAccessor) hooks).getPools(), 0,
        //         LootTableReference.lootTableReference(MORE_RAW_SWEET_POTATOES).setWeight(12));
    }
}
