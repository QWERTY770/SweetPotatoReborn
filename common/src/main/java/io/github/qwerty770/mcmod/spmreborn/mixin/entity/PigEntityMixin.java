package io.github.qwerty770.mcmod.spmreborn.mixin.entity;

import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pig.class)
abstract class PigEntityMixin {
    @Inject(at = @At("RETURN"), method = "isFood(Lnet/minecraft/world/item/ItemStack;)Z", cancellable = true)
    private void isFood(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        Ingredient customFood = Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocationTool.create("spmreborn:pig_breeding_ingredients")));
        if (customFood.test(itemStack)){
            cir.setReturnValue(true);
        }
    }
}
