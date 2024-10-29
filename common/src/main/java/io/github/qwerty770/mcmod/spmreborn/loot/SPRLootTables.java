package io.github.qwerty770.mcmod.spmreborn.loot;

import dev.architectury.event.events.common.LootEvent;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;

public class SPRLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static final ResourceKey<LootTable> RAW_SWEET_POTATOES = createKey("misc/raw_sweet_potatoes");
    public static final ResourceKey<LootTable> MORE_RAW_SWEET_POTATOES = createKey("misc/more_raw_sweet_potatoes");
    public static final ResourceKey<LootTable> MORE_BAKED_SWEET_POTATOES = createKey("misc/more_baked_sweet_potatoes");

    private static final List<ResourceKey<LootTable>> zombies = List.of(EntityType.ZOMBIE.getDefaultLootTable(),
            EntityType.HUSK.getDefaultLootTable(), EntityType.ZOMBIE_VILLAGER.getDefaultLootTable());
    private static final List<ResourceKey<LootTable>> ancient_cities = List.of(BuiltInLootTables.ANCIENT_CITY,
            BuiltInLootTables.ANCIENT_CITY_ICE_BOX);

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register((key, context, builtin) -> {
            if (!isVanilla(key) || !builtin) return;
            if (zombies.contains(key)){
                LootPool.Builder pool = LootPool.lootPool().add(NestedLootTable.lootTableReference(RAW_SWEET_POTATOES))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceCondition.randomChance(0.025F));
                context.addPool(pool);
            } else if (BuiltInLootTables.VILLAGE_PLAINS_HOUSE.equals(key)) {
                context.addPool(VillagerLootTables.plains());
            } else if (BuiltInLootTables.VILLAGE_SNOWY_HOUSE.equals(key)) {
                context.addPool(VillagerLootTables.snowy());
            } else if (BuiltInLootTables.VILLAGE_TAIGA_HOUSE.equals(key)) {
                context.addPool(VillagerLootTables.taiga());
            } else if (BuiltInLootTables.UNDERWATER_RUIN_BIG.equals(key)) {
                context.addPool(UnderwaterRuinLootTables.big());
            } else if (BuiltInLootTables.UNDERWATER_RUIN_SMALL.equals(key)) {
                context.addPool(UnderwaterRuinLootTables.small());
            } else if (BuiltInLootTables.STRONGHOLD_CORRIDOR.equals(key)) {
                context.addPool(StrongholdLootTables.corridor());
            } else if (BuiltInLootTables.STRONGHOLD_CROSSING.equals(key)) {
                context.addPool(StrongholdLootTables.crossing());
            } else if (ancient_cities.contains(key)) {
                context.addPool(DeepDarkLootTables.iceBox());
            } else if (BuiltInLootTables.SHIPWRECK_SUPPLY.equals(key)) {
                context.addPool(MiscLootTables.shipwreckSupply());
            } else if (BuiltInLootTables.PILLAGER_OUTPOST.equals(key)) {
                context.addPool(MiscLootTables.pillagerOutpost());
            } else if (BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
                context.addPool(MiscLootTables.woodlandMansion());
            }
        });
    }

    private static boolean isVanilla(ResourceKey<?> key) {
        return key.location().getNamespace().equals("minecraft");
    }

    public static ResourceKey<LootTable> createKey(String path){
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocationTool.create(SPRMain.MODID, path));
    }
}
