package io.github.qwerty770.mcmod.spmreborn.stats;

import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.resources.ResourceLocation;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.stat;

public class SweetPotatoStats {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("stats");
    // Stats
    public static final ResourceLocation INTERACT_WITH_GRINDER = stat("interact_with_grinder");
    public static final ResourceLocation INTERACT_WITH_AGRO = stat("interact_with_agroforestry_table");
    public static final ResourceLocation CROP_UPGRADED = stat("crop_upgraded");
    public static final ResourceLocation SWEET_POTATO_EATEN = stat("sweet_potato_eaten");
    public static final ResourceLocation INTERACT_WITH_MAGIC_CUBE = stat("interact_with_magic_cube");
}
