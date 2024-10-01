package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = "spmreborn", bus = Mod.EventBusSubscriber.Bus.MOD)
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
