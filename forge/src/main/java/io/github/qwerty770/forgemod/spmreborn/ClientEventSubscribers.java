package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.client.SPRClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "spmreborn", bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public final class ClientEventSubscribers {
    @SubscribeEvent
    public static void initClient(FMLClientSetupEvent event) {
        event.enqueueWork(SPRClient::init);
    }
}
