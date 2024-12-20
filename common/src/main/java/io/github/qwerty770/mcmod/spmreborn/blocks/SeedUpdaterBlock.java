package io.github.qwerty770.mcmod.spmreborn.blocks;

import dev.architectury.registry.menu.MenuRegistry;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.SeedUpdaterScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

// Re：SmithingTableBlock
@ParametersAreNonnullByDefault
public class SeedUpdaterBlock extends CraftingTableBlock {
    private static final Component SCREEN_TITLE = Component.translatable("container.spmreborn.seed_updating");
    protected static final VoxelShape SHAPE = Block.box(
            0.0D, 0.0D, 0.0D,
            16.0D, 12.0D, 16.0D
    );

    public SeedUpdaterBlock(Properties settings) {
        super(settings);
    }

    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider((syncId, inv, player) -> new SeedUpdaterScreenHandler(
                syncId, inv, ContainerLevelAccess.create(world, pos)
        ), SCREEN_TITLE);
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (world.isClientSide)
            return InteractionResult.SUCCESS;
        MenuRegistry.openMenu((ServerPlayer) player, state.getMenuProvider(world, pos)); // Multi-platform support
        player.awardStat(SweetPotatoStats.INTERACT_WITH_AGRO);
        return InteractionResult.CONSUME;
    }
}
