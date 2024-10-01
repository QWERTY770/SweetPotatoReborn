package io.github.qwerty770.mcmod.spmreborn.sound;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.util.registries.InternalRegistryLogWrapper;
import net.minecraft.sounds.SoundEvent;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.sound;

public class SweetPotatoSoundEvents {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("sound_events");
    // Sounds
    public static final RegistrySupplier<SoundEvent> AGROFORESTRY_TABLE_FINISH = sound("block.agroforestry_table.finish");
    public static final RegistrySupplier<SoundEvent> GRINDER_GRIND = sound("block.grinder.grind");
    public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_ACTIVATE = sound("block.magic_cube.activate");
    public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_DEACTIVATE = sound("block.magic_cube.deactivate");
    public static final RegistrySupplier<SoundEvent> MAGIC_CUBE_AMBIENT = sound("block.magic_cube.ambient");
}
