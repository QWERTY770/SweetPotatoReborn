package io.github.qwerty770.mcmod.spmreborn.mixin;

import io.github.qwerty770.mcmod.spmreborn.world.tree.SweetPotatoTreeFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FeatureUtils.class)
public class FeatureUtilsMixin {
    @Inject(at = @At("HEAD"), method = "bootstrap(Lnet/minecraft/data/worldgen/BootstrapContext;)V")
    private static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstapContext, CallbackInfo ci){
        SweetPotatoTreeFeatures.bootstrap(bootstapContext);
    }
}
