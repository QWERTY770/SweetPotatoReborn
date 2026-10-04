package io.github.qwerty770.mcmod.spmreborn.world.levelmeta;

public class SPRLevelPropertiesHelper {
    public static final int DATA_VERSION = 48;  // 48 for 1.21.1

    public static void setLevelSPRDataVersion(SPRLevelProperties SPRLevelProperties, int dataVersion) {
        SPRLevelProperties.sweetPotato_getSPRMetaRaw().putInt("DataVersion", dataVersion);
    }

    public static void setCurrentSPRDataVersion(SPRLevelProperties SPRLevelProperties) {
        setLevelSPRDataVersion(SPRLevelProperties, DATA_VERSION);
    }
}
