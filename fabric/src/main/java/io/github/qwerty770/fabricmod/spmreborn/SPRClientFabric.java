package io.github.qwerty770.fabricmod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.client.SPRClient;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class SPRClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SPRClient.init();
    }
}
