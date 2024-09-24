package io.github.qwerty770.fabricmod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.fabricmc.api.ModInitializer;

@SuppressWarnings("unused")
public class SPRFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SPRMain.register();
        SPRMain.getLogger().info("Sweet Potato Reborn (Fabric platform) loading!");
        SPRMain.init();
    }
}
