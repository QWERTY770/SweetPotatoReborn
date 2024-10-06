package io.github.qwerty770.mcmod.spmreborn.sound;

import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.sound;

public class SweetPotatoSoundEvents {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("sound_events");
    // Sounds
    public static final Supplier<SoundEvent> AGROFORESTRY_TABLE_FINISH = sound("block.agroforestry_table.finish");
    public static final Supplier<SoundEvent> GRINDER_GRIND = sound("block.grinder.grind");
    public static final Supplier<SoundEvent> MAGIC_CUBE_ACTIVATE = sound("block.magic_cube.activate");
    public static final Supplier<SoundEvent> MAGIC_CUBE_DEACTIVATE = sound("block.magic_cube.deactivate");
    public static final Supplier<SoundEvent> MAGIC_CUBE_AMBIENT = sound("block.magic_cube.ambient");
}
