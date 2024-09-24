package io.github.qwerty770.forgemod.spmreborn;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = "spmreborn", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventSubscribers {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        SPRMain.register();
        SPRMain.getLogger().info("Sweet Potato Reborn (Forge platform) loading!");
        SPRMain.init();
    }
}
