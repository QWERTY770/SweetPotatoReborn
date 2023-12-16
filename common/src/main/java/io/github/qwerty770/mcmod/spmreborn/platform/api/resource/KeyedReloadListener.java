package io.github.qwerty770.mcmod.spmreborn.platform.api.resource;

import net.minecraft.resources.ResourceLocation;

// TODO
@Deprecated
public interface KeyedReloadListener extends InternalReloadListener {
    ResourceLocation getId();
}
