package io.github.qwerty770.mcmod.spmreborn.api;

import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import net.minecraft.resources.Identifier;

@StableApi
public class ResourceLocationTool {
    // For compatibility between 1.21 and older versions.
    public static Identifier create(String namespace, String path){
        return Identifier.fromNamespaceAndPath(namespace, path);
    }

    public static Identifier create(String location){
        return Identifier.parse(location);
    }

    public static Identifier withDefaultNamespace(String location){
        return Identifier.withDefaultNamespace(location);
    }
}
