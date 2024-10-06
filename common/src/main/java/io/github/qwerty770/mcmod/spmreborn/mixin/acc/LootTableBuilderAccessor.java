package io.github.qwerty770.mcmod.spmreborn.mixin.acc;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Deprecated
@Mixin(LootTable.Builder.class)
public interface LootTableBuilderAccessor {
    @Accessor
    List<LootPool> getPools();
}
