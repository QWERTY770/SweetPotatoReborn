package io.github.qwerty770.mcmod.spmreborn.linkage;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@FunctionalInterface
@Environment(EnvType.SERVER)
public interface SPRLinkageServer {
    void initServer();

    static void load() {
        FabricLoader.getInstance().getEntrypoints("spmreborn.server", SPRLinkageServer.class)
                .forEach(SPRLinkageServer::initServer);
    }
}
