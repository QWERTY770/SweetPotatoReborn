package io.github.qwerty770.mcmod.spmreborn.util.sweetpotato;

import io.github.qwerty770.mcmod.spmreborn.util.registries.ComposterHelper;
import io.github.qwerty770.mcmod.spmreborn.util.registries.GrindingUtils;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.ItemLike;

import java.util.Objects;

public class SweetPotatoComponent {
    // Update to Minecraft 1.20 -- 2023/12/16
    // Changed the type of grindData from java.util.OptionalDouble to double
    public final int hunger;
    public final float saturationModifier;
    public final float compost;
    public final double grindData;
    public final boolean alwaysEdible;

    public SweetPotatoComponent(int hunger, float sat, float compost, double grindData, boolean alwaysEdible) {
        this.hunger = hunger;
        this.saturationModifier = sat / hunger / 2.0F;   // saturation = food * saturationModifier * 2.0F
        this.grindData = grindData;
        this.compost = compost;
        this.alwaysEdible = alwaysEdible;
    }

    public SweetPotatoComponent(int hunger, float sat, float compost, double grindData) {
        this(hunger, sat, compost, grindData, false);
    }

    public SweetPotatoComponent(int hunger, float sat, float compost){
        this(hunger, sat, compost, -1.0, false);  // -1.0 means not present
    }

    public FoodProperties asFoodComponent() {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(this.hunger)
                .saturationModifier(this.saturationModifier);
        return this.alwaysEdible ? (builder.alwaysEdible().build()) : (builder.build());
    }

    public void registerCompostableItem(SweetPotatoType type, SweetPotatoStatus status) {
        ItemLike item = type.get(status);
        if (item != null)
            ComposterHelper.registerCompostableItem(this.compost, Objects.requireNonNull(item));
    }

    public void registerGrindableItem (SweetPotatoType type, SweetPotatoStatus status) {
        ItemLike item = type.get(status);
        if (item != null){
            if (this.grindData != -1.0){
                GrindingUtils.registerGrindableItem(this.grindData, item);  // grindable
            }
        }
        // Legacy code: this.grindData.ifPresent(aDouble -> GrindingUtils.registerGrindableItem(aDouble, item));
    }
}
