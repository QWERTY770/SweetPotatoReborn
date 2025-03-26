package io.github.qwerty770.mcmod.spmreborn.mixin.advancements;

import io.github.qwerty770.mcmod.spmreborn.util.advancements.AdvancementModification;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerAdvancementManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci){
        ServerAdvancementManager manager = ((MinecraftServer) ((Object) this)).getAdvancements();
        for (AdvancementHolder holder : manager.getAllAdvancements()) {
            AdvancementModification.checkBalancedDiet(holder, BuiltInRegistries.ITEM);
        }
    }
}
