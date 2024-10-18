package io.github.qwerty770.mcmod.spmreborn.blocks;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import dev.architectury.registry.menu.MenuRegistry;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.MagicCubeBlockEntity;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.SweetPotatoBlockEntityTypes;
import io.github.qwerty770.mcmod.spmreborn.lib.blockentity.AbstractBlockWithEntity;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MagicCubeBlock extends AbstractBlockWithEntity<MagicCubeBlockEntity> {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");


    @Override
    public @NotNull MapCodec<MagicCubeBlock> codec(){
        return simpleCodec(MagicCubeBlock::new);
    }

    @Override
    protected boolean blockEntityPredicate(BlockEntity blockEntity) {
        return blockEntity instanceof MagicCubeBlockEntity;
    }

    @Override
    public MagicCubeBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MagicCubeBlockEntity(pos, state);
    }

    @Override
    public List<ResourceLocation> incrementWhileOnUse(BlockState state, Level world, BlockPos pos, ServerPlayer serverPlayerEntity, BlockHitResult blockHitResult) {
        return ImmutableList.of(SweetPotatoStats.INTERACT_WITH_MAGIC_CUBE);
    }

    @Override
    public BlockEntityType<MagicCubeBlockEntity> getBlockEntityType() {
        return SweetPotatoBlockEntityTypes.MAGIC_CUBE_BLOCK_ENTITY_TYPE.get();
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide && state.getBlock() instanceof MagicCubeBlock) {
            if (!state.getValue(ACTIVATED)) return InteractionResult.PASS;
            if (world.getBlockEntity(pos) instanceof MagicCubeBlockEntity blockEntity) {
                MenuRegistry.openExtendedMenu((ServerPlayer) player, blockEntity); // Multi-platform support
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    public MagicCubeBlock(Properties settings) {
        super(settings);
        registerDefaultState(this.getStateDefinition().any().setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
}
