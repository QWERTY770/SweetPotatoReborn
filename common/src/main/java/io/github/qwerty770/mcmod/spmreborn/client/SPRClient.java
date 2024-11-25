package io.github.qwerty770.mcmod.spmreborn.client;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;

@Environment(EnvType.CLIENT)
public class SPRClient {
    public static void init() {
        /* Color Providers */
        // Update to Minecraft 1.20 -- 2023/11/26 and 2023/12/16
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                SweetPotatoBlocks.ENCHANTED_ACACIA_LEAVES.get(), SweetPotatoBlocks.ENCHANTED_DARK_OAK_LEAVES.get(),
                SweetPotatoBlocks.ENCHANTED_JUNGLE_LEAVES.get(), SweetPotatoBlocks.ENCHANTED_OAK_LEAVES.get()
        );
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), SweetPotatoBlocks.ENCHANTED_BIRCH_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> FoliageColor.getEvergreenColor(), SweetPotatoBlocks.ENCHANTED_SPRUCE_LEAVES.get());
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getDefaultColor(),
                SweetPotatoItems.ENCHANTED_ACACIA_LEAVES_ITEM.get(), SweetPotatoItems.ENCHANTED_DARK_OAK_LEAVES_ITEM.get(),
                SweetPotatoItems.ENCHANTED_JUNGLE_LEAVES_ITEM.get(), SweetPotatoItems.ENCHANTED_OAK_LEAVES_ITEM.get()
        );
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getBirchColor(), SweetPotatoItems.ENCHANTED_BIRCH_LEAVES_ITEM.get());
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.getEvergreenColor(), SweetPotatoItems.ENCHANTED_SPRUCE_LEAVES_ITEM.get());

        /* Linkage */
        // FabricLoader.getInstance().getEntrypoints("spmreborn.client", SPRLinkageClient.class).forEach(SPRLinkageClient::initClient);
        /* Rendering */
        // Update to Minecraft 1.20 -- 2023/12/3 and 2023/12/16
        RenderTypeRegistry.register(RenderType.cutout(),
                SweetPotatoBlocks.PURPLE_POTATO_CROP.get(), SweetPotatoBlocks.RED_POTATO_CROP.get(),
                SweetPotatoBlocks.WHITE_POTATO_CROP.get(), SweetPotatoBlocks.SEED_UPDATER.get(),
                SweetPotatoBlocks.ENCHANTED_ACACIA_SAPLING.get(), SweetPotatoBlocks.ENCHANTED_BIRCH_SAPLING.get(),
                SweetPotatoBlocks.ENCHANTED_DARK_OAK_SAPLING.get(), SweetPotatoBlocks.ENCHANTED_OAK_SAPLING.get(),
                SweetPotatoBlocks.ENCHANTED_JUNGLE_SAPLING.get(), SweetPotatoBlocks.ENCHANTED_SPRUCE_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_ACACIA_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_BIRCH_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_DARK_OAK_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_JUNGLE_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_OAK_SAPLING.get(),
                SweetPotatoBlocks.POTTED_ENCHANTED_SPRUCE_SAPLING.get(),
                SweetPotatoBlocks.ENCHANTED_BEETROOTS_CROP.get(), SweetPotatoBlocks.ENCHANTED_CARROTS_CROP.get(),
                SweetPotatoBlocks.ENCHANTED_VANILLA_POTATOES_CROP.get(), SweetPotatoBlocks.ENCHANTED_WHEAT_CROP.get(),
                SweetPotatoBlocks.ENCHANTED_SUGAR_CANE.get()
        );
    }
}
