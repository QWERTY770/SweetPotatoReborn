package io.github.qwerty770.mcmod.spmreborn.world.levelmeta;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.WorldData;

public class SPRLevelPropertiesHelper {
    public static final int DATA_VERSION = 15;  // 15 for 1.20.1

    public static int getLevelSPRDataVersion(MinecraftServer server) {
        return getLevelSPRDataVersion(server.getWorldData());
    }

    public static int getLevelSPRDataVersion(WorldData properties) {
        return getLevelSPRDataVersion((SPRLevelProperties) properties);
    }

    /* Zero: wasn't loaded */
    public static int getLevelSPRDataVersion(SPRLevelProperties SPRLevelProperties) {
        CompoundTag tag = SPRLevelProperties.sweetPotato_getSPRMetaRaw();
        return tag.getInt("DataVersion");
    }

    public static void setLevelSPRDataVersion(SPRLevelProperties SPRLevelProperties, int dataVersion) {
        SPRLevelProperties.sweetPotato_getSPRMetaRaw().putInt("DataVersion", dataVersion);
    }

    public static void setCurrentSPRDataVersion(SPRLevelProperties SPRLevelProperties) {
        setLevelSPRDataVersion(SPRLevelProperties, DATA_VERSION);
    }
}
