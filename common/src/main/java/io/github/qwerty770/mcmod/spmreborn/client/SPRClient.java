package io.github.qwerty770.mcmod.spmreborn.client;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SPRClient {
    public static void init() {
        /* Color Providers */
        // Update to Minecraft 1.21.4 2025/1/18
        ColorHandlerRegistry.registerBlockColors(new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        return FoliageColor.FOLIAGE_DEFAULT;
                    }

                    @Override
                    public int colorInWorld(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos) {
                        return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT;
                    }
                },
                SweetPotatoBlocks.ENCHANTED_ACACIA_LEAVES.get(), SweetPotatoBlocks.ENCHANTED_DARK_OAK_LEAVES.get(),
                SweetPotatoBlocks.ENCHANTED_JUNGLE_LEAVES.get(), SweetPotatoBlocks.ENCHANTED_OAK_LEAVES.get()
        );
        ColorHandlerRegistry.registerBlockColors((state) -> FoliageColor.FOLIAGE_BIRCH, SweetPotatoBlocks.ENCHANTED_BIRCH_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state) -> FoliageColor.FOLIAGE_EVERGREEN, SweetPotatoBlocks.ENCHANTED_SPRUCE_LEAVES.get());
//        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.FOLIAGE_DEFAULT,
//                SweetPotatoItems.ENCHANTED_ACACIA_LEAVES_ITEM.get(), SweetPotatoItems.ENCHANTED_DARK_OAK_LEAVES_ITEM.get(),
//                SweetPotatoItems.ENCHANTED_JUNGLE_LEAVES_ITEM.get(), SweetPotatoItems.ENCHANTED_OAK_LEAVES_ITEM.get()
//        );
//        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.FOLIAGE_BIRCH, SweetPotatoItems.ENCHANTED_BIRCH_LEAVES_ITEM.get());
//        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.FOLIAGE_EVERGREEN, SweetPotatoItems.ENCHANTED_SPRUCE_LEAVES_ITEM.get());

        /* Linkage */
        // FabricLoader.getInstance().getEntrypoints("spmreborn.client", SPRLinkageClient.class).forEach(SPRLinkageClient::initClient);
    }
}
