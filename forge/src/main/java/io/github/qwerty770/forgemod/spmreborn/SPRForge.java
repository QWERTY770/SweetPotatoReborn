package io.github.qwerty770.forgemod.spmreborn;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("spmreborn")
public class SPRForge {
    public SPRForge() {
        EventBuses.registerModEventBus("spmreborn", FMLJavaModLoadingContext.get().getModEventBus());
    }
}

