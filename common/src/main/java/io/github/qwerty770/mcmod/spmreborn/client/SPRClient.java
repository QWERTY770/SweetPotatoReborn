package io.github.qwerty770.mcmod.spmreborn.client;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.linkage.SPRLinkageClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;

@Environment(EnvType.CLIENT)
public class SPRClient {
    public static void init() {
        /* Client Screens */
        // Update to Minecraft 1.20 -- 2023/12/3
        MenuRegistry.registerScreenFactory(SPRMain.SEED_UPDATER_SCREEN_HANDLER_TYPE.get(), SeedUpdaterScreen::new);
        MenuRegistry.registerScreenFactory(SPRMain.GRINDER_SCREEN_HANDLER_TYPE.get(), GrinderScreen::new);
        MenuRegistry.registerScreenFactory(SPRMain.MAGIC_CUBE_SCREEN_HANDLER_TYPE.get(), MagicCubeScreen::new);

        /* Color Providers */
        // Update to Minecraft 1.20 -- 2023/11/26 and 2023/12/16
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                SPRMain.ENCHANTED_ACACIA_LEAVES.get(), SPRMain.ENCHANTED_DARK_OAK_LEAVES.get(),
                SPRMain.ENCHANTED_JUNGLE_LEAVES.get(), SPRMain.ENCHANTED_OAK_LEAVES.get()
        );
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), SPRMain.ENCHANTED_BIRCH_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getEvergreenColor(), SPRMain.ENCHANTED_SPRUCE_LEAVES.get());
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getDefaultColor(),
                SPRMain.ENCHANTED_ACACIA_LEAVES_ITEM.get(), SPRMain.ENCHANTED_DARK_OAK_LEAVES_ITEM.get(),
                SPRMain.ENCHANTED_JUNGLE_LEAVES_ITEM.get(), SPRMain.ENCHANTED_OAK_LEAVES_ITEM.get()
        );
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getBirchColor(), SPRMain.ENCHANTED_BIRCH_LEAVES_ITEM.get());
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getEvergreenColor(), SPRMain.ENCHANTED_SPRUCE_LEAVES_ITEM.get());

        /* Linkage */
        FabricLoader.getInstance().getEntrypoints("spmreborn.client", SPRLinkageClient.class).forEach(SPRLinkageClient::initClient);

        /* Rendering */
        // Update to Minecraft 1.20 -- 2023/12/3 and 2023/12/16
        RenderTypeRegistry.register(RenderType.cutout(),
                SPRMain.PURPLE_POTATO_CROP.get(), SPRMain.RED_POTATO_CROP.get(),
                SPRMain.WHITE_POTATO_CROP.get(), SPRMain.SEED_UPDATER.get(),
                SPRMain.ENCHANTED_ACACIA_SAPLING.get(), SPRMain.ENCHANTED_BIRCH_SAPLING.get(),
                SPRMain.ENCHANTED_DARK_OAK_SAPLING.get(), SPRMain.ENCHANTED_OAK_SAPLING.get(),
                SPRMain.ENCHANTED_JUNGLE_SAPLING.get(), SPRMain.ENCHANTED_SPRUCE_SAPLING.get(),
                //SPMMain.GRINDER,
                SPRMain.POTTED_ENCHANTED_ACACIA_SAPLING.get(),
                SPRMain.POTTED_ENCHANTED_BIRCH_SAPLING.get(),
                SPRMain.POTTED_ENCHANTED_DARK_OAK_SAPLING.get(),
                SPRMain.POTTED_ENCHANTED_JUNGLE_SAPLING.get(),
                SPRMain.POTTED_ENCHANTED_OAK_SAPLING.get(),
                SPRMain.POTTED_ENCHANTED_SPRUCE_SAPLING.get(),
                SPRMain.ENCHANTED_BEETROOTS_CROP.get(), SPRMain.ENCHANTED_CARROTS_CROP.get(),
                SPRMain.ENCHANTED_VANILLA_POTATOES_CROP.get(), SPRMain.ENCHANTED_WHEAT_CROP.get(),
                SPRMain.ENCHANTED_SUGAR_CANE.get()
        );
    }
}
