package io.github.qwerty770.mcmod.spmreborn.items;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import io.github.qwerty770.mcmod.spmreborn.loot.SetEnchantedPotatoEffectFunction.EffectEntry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.componentType;

public class SweetPotatoDataComponentTypes {
    // Update to Minecraft 1.21 -- 2024/10/17  Replace NBT with Data Component
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("data_components");
    // "statusEffects" tag in 1.20
    public static final RegistrySupplier<DataComponentType<List<MobEffectInstance>>> STATUS_EFFECTS = componentType("status_effects",
            () -> new DataComponentType.Builder<List<MobEffectInstance>>().persistent(EffectEntry.EFFECT_CODEC.listOf()).build());
    // "displayIndex" tag in 1.20
    public static final RegistrySupplier<DataComponentType<Integer>> DISPLAY_INDEX = componentType("display_index",
            () -> new DataComponentType.Builder<Integer>().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
}
