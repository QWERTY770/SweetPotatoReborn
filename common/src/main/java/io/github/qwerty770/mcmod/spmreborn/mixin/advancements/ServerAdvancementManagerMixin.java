package io.github.qwerty770.mcmod.spmreborn.mixin.advancements;

import io.github.qwerty770.mcmod.spmreborn.util.advancements.AdvancementModification;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.ServerAdvancementManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerAdvancementManager.class)
public class ServerAdvancementManagerMixin {
    @SuppressWarnings("UnreachableCode")
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void apply(CallbackInfo ci) {
        ServerAdvancementManager manager = (ServerAdvancementManager) ((Object) this);
        for (AdvancementHolder holder : manager.getAllAdvancements()) {
            AdvancementModification.checkBalancedDiet(holder, BuiltInRegistries.ITEM);
        }
    }
}