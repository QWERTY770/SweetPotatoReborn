package io.github.qwerty770.mcmod.spmreborn.linkage;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface SPRLinkageClient {
    void initClient();
}
