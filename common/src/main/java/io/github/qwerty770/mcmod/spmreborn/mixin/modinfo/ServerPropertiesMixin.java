package io.github.qwerty770.mcmod.spmreborn.mixin.modinfo;

import io.github.qwerty770.mcmod.spmreborn.world.levelmeta.SPRLevelProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldData.class)
public interface ServerPropertiesMixin extends SPRLevelProperties {
    @Override
    default CompoundTag sweetPotato_getSPRMetaRaw() {
        return new CompoundTag();
    }

    @Override
    default void sweetPotato_setSPRMetaRaw(CompoundTag tag) {
    }
}
