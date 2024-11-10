package io.github.qwerty770.mcmod.spmreborn.loot;

import dev.architectury.event.events.common.LootEvent;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;

public class SPRLootTables {
    // Update to Minecraft 1.20 -- 2023/12/9
    // Removed io.github.qwerty770.mcmod.spmreborn.loot.LootUtils because it requires Fabric API
    // The following functions now return LootPool.Builder in order to adapt to LootEvent of Architectury API
    public static final ResourceLocation RAW_SWEET_POTATOES, MORE_RAW_SWEET_POTATOES,
                MORE_BAKED_SWEET_POTATOES;

    static {
        RAW_SWEET_POTATOES = ResourceLocationTool.create(SPRMain.MODID, "misc/raw_sweet_potatoes");
        MORE_RAW_SWEET_POTATOES = ResourceLocationTool.create(SPRMain.MODID, "misc/more_raw_sweet_potatoes");
        MORE_BAKED_SWEET_POTATOES = ResourceLocationTool.create(SPRMain.MODID, "misc/more_baked_sweet_potatoes");
    }

    private static final List<ResourceLocation> zombies = List.of(EntityType.ZOMBIE.getDefaultLootTable(),
            EntityType.HUSK.getDefaultLootTable(), EntityType.ZOMBIE_VILLAGER.getDefaultLootTable());

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register((lootDataManager, identifier, context, builtin) -> {
            if (!isVanilla(identifier) || !builtin) return;
            if (zombies.contains(identifier)){
                LootPool.Builder pool = LootPool.lootPool().add(LootTableReference.lootTableReference(RAW_SWEET_POTATOES))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceCondition.randomChance(0.025F));
                context.addPool(pool);
            } else if (BuiltInLootTables.VILLAGE_PLAINS_HOUSE.equals(identifier)) {
                context.addPool(VillagerLootTables.plains());
            } else if (BuiltInLootTables.VILLAGE_SNOWY_HOUSE.equals(identifier)) {
                context.addPool(VillagerLootTables.snowy());
            } else if (BuiltInLootTables.VILLAGE_TAIGA_HOUSE.equals(identifier)) {
                context.addPool(VillagerLootTables.taiga());
            } else if (BuiltInLootTables.UNDERWATER_RUIN_BIG.equals(identifier)) {
                context.addPool(UnderwaterRuinLootTables.big());
            } else if (BuiltInLootTables.UNDERWATER_RUIN_SMALL.equals(identifier)) {
                context.addPool(UnderwaterRuinLootTables.small());
            } else if (BuiltInLootTables.STRONGHOLD_CORRIDOR.equals(identifier)) {
                context.addPool(StrongholdLootTables.corridor());
            } else if (BuiltInLootTables.STRONGHOLD_CROSSING.equals(identifier)) {
                context.addPool(StrongholdLootTables.crossing());
            } else if (BuiltInLootTables.ANCIENT_CITY.equals(identifier) || BuiltInLootTables.ANCIENT_CITY_ICE_BOX.equals(identifier)) {
                context.addPool(DeepDarkLootTables.iceBox());
            } else if (BuiltInLootTables.SHIPWRECK_SUPPLY.equals(identifier)) {
                context.addPool(MiscLootTables.shipwreckSupply());
            } else if (BuiltInLootTables.PILLAGER_OUTPOST.equals(identifier)) {
                context.addPool(MiscLootTables.pillagerOutpost());
            } else if (BuiltInLootTables.WOODLAND_MANSION.equals(identifier)) {
                context.addPool(MiscLootTables.woodlandMansion());
            }
        });
    }

    static boolean isVanilla(ResourceLocation identifier) {
        return identifier.getNamespace().equals("minecraft");
    }
}
