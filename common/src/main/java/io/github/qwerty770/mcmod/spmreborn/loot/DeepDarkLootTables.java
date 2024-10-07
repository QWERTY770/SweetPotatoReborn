package io.github.qwerty770.mcmod.spmreborn.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class DeepDarkLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static LootPool.Builder iceBox() {
        LootPool.Builder pool = LootPool.lootPool();

        pool.add(NestedLootTable.lootTableReference(SPRLootTables.createKey("misc/vanilla/ancient_city_stew")));
        pool.add(NestedLootTable.lootTableReference(SPRLootTables.MORE_BAKED_SWEET_POTATOES));
        return pool;
    }
}
