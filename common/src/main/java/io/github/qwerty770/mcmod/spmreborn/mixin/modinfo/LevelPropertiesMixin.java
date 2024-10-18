package io.github.qwerty770.mcmod.spmreborn.mixin.modinfo;

import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import io.github.qwerty770.mcmod.spmreborn.world.levelmeta.SPRLevelProperties;
import io.github.qwerty770.mcmod.spmreborn.world.levelmeta.SPRLevelPropertiesHelper;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class LevelPropertiesMixin implements SPRLevelProperties {
    @Unique
    private CompoundTag sweetPotato_sprMeta = new CompoundTag();

    @Override
    public CompoundTag sweetPotato_getSPRMetaRaw() {
        return this.sweetPotato_sprMeta;
    }

    @Override
    public void sweetPotato_setSPRMetaRaw(CompoundTag tag) {
        this.sweetPotato_sprMeta = tag;
    }

    @Inject(at = @At("RETURN"), method = "parse")
    private static void onReadProperties(Dynamic<?> tag, LevelSettings levelSettings, PrimaryLevelData.SpecialWorldProperty specialWorldProperty, WorldOptions worldOptions, Lifecycle worldGenSettingsLifecycle, CallbackInfoReturnable<PrimaryLevelData> cir) {
        PrimaryLevelData levelProperties = cir.getReturnValue();
        SPRLevelPropertiesHelper.setCurrentSPRDataVersion((SPRLevelProperties) levelProperties);
    }

    @Inject(at = @At("RETURN"), method = "setTagData")
    private void onWriteNbt(RegistryAccess dynamicRegistryManager, CompoundTag root, CompoundTag playerData, CallbackInfo ci) {
        root.put("spmreborn:custom_data", this.sweetPotato_sprMeta);
    }
}
