package io.github.qwerty770.mcmod.spmreborn.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jetbrains.annotations.NotNull;

public record SeedUpdatingRecipeDisplay(SlotDisplay base, SlotDisplay addition, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    // see net.minecraft.world.item.crafting.display.SmithingRecipeDisplay
    public static final MapCodec<SeedUpdatingRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.fieldOf("base").forGetter(SeedUpdatingRecipeDisplay::base),
            SlotDisplay.CODEC.fieldOf("addition").forGetter(SeedUpdatingRecipeDisplay::addition),
            SlotDisplay.CODEC.fieldOf("result").forGetter(SeedUpdatingRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(SeedUpdatingRecipeDisplay::craftingStation))
            .apply(instance, SeedUpdatingRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SeedUpdatingRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC, SeedUpdatingRecipeDisplay::base,
            SlotDisplay.STREAM_CODEC, SeedUpdatingRecipeDisplay::addition,
            SlotDisplay.STREAM_CODEC, SeedUpdatingRecipeDisplay::result,
            SlotDisplay.STREAM_CODEC, SeedUpdatingRecipeDisplay::craftingStation,
            SeedUpdatingRecipeDisplay::new);
    public static final RecipeDisplay.Type<SeedUpdatingRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

    public RecipeDisplay.@NotNull Type<SeedUpdatingRecipeDisplay> type() {
        return TYPE;
    }
}
