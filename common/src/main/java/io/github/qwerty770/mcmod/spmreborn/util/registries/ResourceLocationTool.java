package io.github.qwerty770.mcmod.spmreborn.util.registries;

import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public class ResourceLocationTool {
    // For compatibility between 1.21 and older versions.
    public static ResourceLocation create(String namespace, String path){
        return new ResourceLocation(namespace, path);
    }

    public static ResourceLocation create(String location){
        return new ResourceLocation(location);
    }

    public static ResourceLocation withDefaultNamespace(String location){
        return new ResourceLocation("minecraft", location);
    }
}
