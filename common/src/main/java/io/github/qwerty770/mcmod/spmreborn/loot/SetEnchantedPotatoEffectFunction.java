package io.github.qwerty770.mcmod.spmreborn.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.qwerty770.mcmod.spmreborn.items.EnchantedSweetPotatoItem;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoProperties;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoStatus;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
public class SetEnchantedPotatoEffectFunction extends LootItemConditionalFunction {
    private final List<EffectEntry> effects;
    private final @Nullable Integer displayIndex;
    public static final MapCodec<SetEnchantedPotatoEffectFunction> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    LootItemCondition.DIRECT_CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter((function) -> function.predicates),
                    EffectEntry.CODEC.listOf().fieldOf("effects").forGetter((function -> function.effects)),
                    Codec.INT.optionalFieldOf("displayIndex", -1).forGetter(function -> function.displayIndex))
            .apply(instance, SetEnchantedPotatoEffectFunction::new)
    );

    private SetEnchantedPotatoEffectFunction(List<LootItemCondition> lootItemConditions,
                                               List<EffectEntry> effects,
                                               @Nullable Integer displayIndex) {
        super(lootItemConditions);
        this.effects = effects;
        this.displayIndex = displayIndex;
    }

    @Override
    protected @NotNull ItemStack run(ItemStack itemStack, LootContext lootContext) {
        if (!((itemStack.getItem() instanceof SweetPotatoProperties spp) &&
                spp.getStatus().equals(SweetPotatoStatus.ENCHANTED)))
            return itemStack;
        Stream<MobEffectInstance> stream = effects.stream().flatMap(p -> {
            var source = lootContext.getRandom();
            if (source.nextFloat() < p.chance)
                return Stream.of(p.effect);
            return Stream.empty();
        });
        EnchantedSweetPotatoItem.applyEffects(itemStack, stream.toList(), displayIndex);
        return itemStack;
    }

    @Override
    public @NotNull LootItemFunctionType<SetEnchantedPotatoEffectFunction> getType() {
        return SweetPotatoLootFunctions.SET_ENCHANTED_POTATO_EFFECT.get();
    }

    public record EffectEntry(MobEffectInstance effect, float chance) {
        public static final MapCodec<MobEffectInstance> EFFECT_MAP_CODEC = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(MobEffect.CODEC.fieldOf("id").forGetter(MobEffectInstance::getEffect),
                                Codec.INT.optionalFieldOf("duration", 100).forGetter(MobEffectInstance::getDuration),
                                Codec.INT.optionalFieldOf("amplifier", 0).forGetter(MobEffectInstance::getAmplifier))
                        .apply(instance, MobEffectInstance::new)
        );
        public static final Codec<MobEffectInstance> EFFECT_CODEC = EFFECT_MAP_CODEC.codec();
        public static final Codec<SetEnchantedPotatoEffectFunction.EffectEntry> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(EFFECT_MAP_CODEC.forGetter(EffectEntry::effect),
                                Codec.FLOAT.optionalFieldOf("chance", 1.0f).forGetter(EffectEntry::chance))
                        .apply(instance, EffectEntry::new)
        );

        public MobEffectInstance effect() {
            return this.effect;
        }

        public float chance() {
            return this.chance;
        }
    }
}
