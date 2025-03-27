package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.client.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = "spmreborn", bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public final class ClientEventSubscribers {
    @SubscribeEvent
    public static void initClient(FMLClientSetupEvent event) {
        event.enqueueWork(SPRClient::init);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event){
        event.register(SweetPotatoMenuTypes.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(), SeedUpdaterScreen::new);
        event.register(SweetPotatoMenuTypes.GRINDER_SCREEN_HANDLER_TYPE.get(), GrinderScreen::new);
        event.register(SweetPotatoMenuTypes.MAGIC_CUBE_SCREEN_HANDLER_TYPE.get(), MagicCubeScreen::new);
    }
}
