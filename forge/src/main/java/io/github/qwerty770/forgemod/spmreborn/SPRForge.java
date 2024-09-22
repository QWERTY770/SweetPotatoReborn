package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.client.SPRClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("spmreborn")
public class SPRForge {
    public SPRForge() {}

    @Mod.EventBusSubscriber(modid = "spmreborn", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ModBusSubscribers {
        @SubscribeEvent
        public static void startup(FMLCommonSetupEvent event) {
            SPRMain.getLogger().info("Sweet Potato Reborn (Forge platform) loading!");
            SPRMain.init();
        }
    }

    @Mod.EventBusSubscriber(modid = "spmreborn", bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT)
    public static final class ClientSubscribers {
        @SubscribeEvent
        public static void initClient(FMLClientSetupEvent event) {
            event.enqueueWork(SPRClient::init);
        }
    }
}
