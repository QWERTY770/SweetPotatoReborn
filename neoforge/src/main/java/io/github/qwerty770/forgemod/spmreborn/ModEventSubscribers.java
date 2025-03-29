package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = "spmreborn", bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscribers {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        SPRMain.getLogger().info("Sweet Potato Reborn (Forge platform) loading!");
        SPRMain.init();
    }

    private static boolean registered = false;
    @SubscribeEvent
    public static void register(RegisterEvent event){
        if (!registered){
            SPRMain.register();
            registered = true;
        }
    }
}
