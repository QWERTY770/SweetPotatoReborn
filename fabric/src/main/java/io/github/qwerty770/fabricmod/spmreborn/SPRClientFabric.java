package io.github.qwerty770.fabricmod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

@SuppressWarnings("unused")
public class SPRClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /* Client Screens */
        MenuScreens.register(SweetPotatoMenuTypes.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(), SeedUpdaterScreen::new);
        MenuScreens.register(SweetPotatoMenuTypes.GRINDER_SCREEN_HANDLER_TYPE.get(), GrinderScreen::new);
        MenuScreens.register(SweetPotatoMenuTypes.MAGIC_CUBE_SCREEN_HANDLER_TYPE.get(), MagicCubeScreen::new);
        /* Initialize */
        SPRClient.init();
    }
}
