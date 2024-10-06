package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.client.SPRClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = "spmreborn", bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public final class ClientEventSubscribers {
    @SubscribeEvent
    public static void initClient(FMLClientSetupEvent event) {
        event.enqueueWork(SPRClient::init);
    }
}
