package io.github.qwerty770.mcmod.spmreborn.mixin;

import io.github.qwerty770.mcmod.spmreborn.advancement.BalancedDietHelper;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Builder.class)
public abstract class AdvancementTaskMixin {
    @Inject(method = "build", at = @At("RETURN"), cancellable = true)
    private void onModifyAdvancement(ResourceLocation id, CallbackInfoReturnable<AdvancementHolder> cir) {
        if ("husbandry/balanced_diet".equals(id.getPath()) && "minecraft".equals(id.getNamespace())) {
            cir.setReturnValue(BalancedDietHelper.setupCriteria(cir.getReturnValue()));
        }
    }
}
