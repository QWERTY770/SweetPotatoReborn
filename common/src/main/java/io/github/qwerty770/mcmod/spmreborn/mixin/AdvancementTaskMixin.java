package io.github.qwerty770.mcmod.spmreborn.mixin;

import com.google.gson.JsonObject;
import io.github.qwerty770.mcmod.spmreborn.advancement.BalancedDietHelper;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Builder.class)
public abstract class AdvancementTaskMixin {
    @Inject(method = "fromJson", at = @At("RETURN"))
    private static void onModifyFromJson(JsonObject obj, DeserializationContext predicateDeserializer, CallbackInfoReturnable<Builder> cir) {
        ResourceLocation id = predicateDeserializer.getAdvancementId();
        if ("balanced_diet".equals(id.getPath()) && "minecraft".equals(id.getNamespace())) {
            BalancedDietHelper.setupCriteria(cir.getReturnValue());
        }
    }
}
