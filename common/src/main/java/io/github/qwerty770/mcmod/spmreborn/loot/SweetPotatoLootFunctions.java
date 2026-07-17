package io.github.qwerty770.mcmod.spmreborn.loot;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.lootFunction;

public class SweetPotatoLootFunctions {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("loot_functions");
    // Loot
    public static final RegistrySupplier<MapCodec<? extends LootItemFunction>> SET_ENCHANTED_POTATO_EFFECT =
            lootFunction("set_enchanted_potato_effect", SetEnchantedPotatoEffectFunction.CODEC);
}
