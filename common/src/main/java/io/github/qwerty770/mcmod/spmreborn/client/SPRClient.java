package io.github.qwerty770.mcmod.spmreborn.client;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.linkage.SPRLinkageClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class SPRClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /* Client Screens */
        // Update to Minecraft 1.20 -- 2023/12/3
        MenuRegistry.registerScreenFactory(SPRMain.SEED_UPDATER_SCREEN_HANDLER_TYPE, SeedUpdaterScreen::new);
        MenuRegistry.registerScreenFactory(SPRMain.GRINDER_SCREEN_HANDLER_TYPE, GrinderScreen::new);
        MenuRegistry.registerScreenFactory(SPRMain.MAGIC_CUBE_SCREEN_HANDLER_TYPE, MagicCubeScreen::new);

        /* Color Providers */
        // Update to Minecraft 1.20 -- 2023/11/26
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                SPRMain.ENCHANTED_ACACIA_LEAVES, SPRMain.ENCHANTED_DARK_OAK_LEAVES,
                SPRMain.ENCHANTED_JUNGLE_LEAVES, SPRMain.ENCHANTED_OAK_LEAVES
        );
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), SPRMain.ENCHANTED_BIRCH_LEAVES);
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getEvergreenColor(), SPRMain.ENCHANTED_SPRUCE_LEAVES);
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getDefaultColor(),
                SPRMain.ENCHANTED_ACACIA_LEAVES_ITEM, SPRMain.ENCHANTED_DARK_OAK_LEAVES_ITEM,
                SPRMain.ENCHANTED_JUNGLE_LEAVES_ITEM, SPRMain.ENCHANTED_OAK_LEAVES_ITEM
        );
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getBirchColor(), SPRMain.ENCHANTED_BIRCH_LEAVES_ITEM);
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getEvergreenColor(), SPRMain.ENCHANTED_SPRUCE_LEAVES_ITEM);

        /* Linkage */
        FabricLoader.getInstance().getEntrypoints("spmreborn.client", SPRLinkageClient.class).forEach(SPRLinkageClient::initClient);

        /* Rendering */
        // Update to Minecraft 1.20 -- 2023/12/3
        RenderTypeRegistry.register(RenderType.cutout(),
                SPRMain.PURPLE_POTATO_CROP, SPRMain.RED_POTATO_CROP,
                SPRMain.WHITE_POTATO_CROP, SPRMain.SEED_UPDATER,
                SPRMain.ENCHANTED_ACACIA_SAPLING, SPRMain.ENCHANTED_BIRCH_SAPLING,
                SPRMain.ENCHANTED_DARK_OAK_SAPLING, SPRMain.ENCHANTED_OAK_SAPLING,
                SPRMain.ENCHANTED_JUNGLE_SAPLING, SPRMain.ENCHANTED_SPRUCE_SAPLING,
                //SPMMain.GRINDER,
                SPRMain.POTTED_ENCHANTED_ACACIA_SAPLING,
                SPRMain.POTTED_ENCHANTED_BIRCH_SAPLING,
                SPRMain.POTTED_ENCHANTED_DARK_OAK_SAPLING,
                SPRMain.POTTED_ENCHANTED_JUNGLE_SAPLING,
                SPRMain.POTTED_ENCHANTED_OAK_SAPLING,
                SPRMain.POTTED_ENCHANTED_SPRUCE_SAPLING,
                SPRMain.ENCHANTED_BEETROOTS_CROP, SPRMain.ENCHANTED_CARROTS_CROP,
                SPRMain.ENCHANTED_VANILLA_POTATOES_CROP, SPRMain.ENCHANTED_WHEAT_CROP,
                SPRMain.ENCHANTED_SUGAR_CANE
        );
    }
}
