package io.github.qwerty770.mcmod.spmreborn.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.qwerty770.mcmod.spmreborn.items.EnchantedSweetPotatoItem;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoProperties;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoStatus;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
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
                return Stream.of(p.getEffect());
            return Stream.empty();
        });
        EnchantedSweetPotatoItem.applyEffects(itemStack, stream.toList(), displayIndex);
        return itemStack;
    }

    @Override
    public @NotNull LootItemFunctionType<SetEnchantedPotatoEffectFunction> getType() {
        return SweetPotatoLootFunctions.SET_ENCHANTED_POTATO_EFFECT.get();
    }

    public record EffectEntry(Holder<MobEffect> effect, int duration, int amplifier, float chance) {
        public static final Codec<SetEnchantedPotatoEffectFunction.EffectEntry> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(MobEffect.CODEC.fieldOf("id").forGetter(EffectEntry::effect),
                                Codec.INT.optionalFieldOf("duration", 100).forGetter(EffectEntry::duration),
                                Codec.INT.optionalFieldOf("amplifier", 0).forGetter(EffectEntry::amplifier),
                                Codec.FLOAT.optionalFieldOf("chance", 1.0f).forGetter(EffectEntry::chance))
                        .apply(instance, EffectEntry::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, EffectEntry> STREAM_CODEC = StreamCodec.composite(
                MobEffect.STREAM_CODEC, EffectEntry::effect,
                ByteBufCodecs.VAR_INT, EffectEntry::duration,
                ByteBufCodecs.VAR_INT, EffectEntry::amplifier,
                ByteBufCodecs.FLOAT, EffectEntry::chance,
                EffectEntry::new
        );


        public EffectEntry(MobEffectInstance effect){
            this(effect.getEffect(), effect.getDuration(), effect.getAmplifier(), 1.0f);
        }

        public MobEffectInstance getEffect() {
            return new MobEffectInstance(effect, duration, amplifier);
        }
    }
}
