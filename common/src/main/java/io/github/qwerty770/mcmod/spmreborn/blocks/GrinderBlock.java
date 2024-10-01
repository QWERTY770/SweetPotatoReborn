package io.github.qwerty770.mcmod.spmreborn.blocks;

import com.google.common.collect.ImmutableList;
import io.github.qwerty770.mcmod.spmreborn.blocks.entities.GrinderBlockEntity;
import io.github.qwerty770.mcmod.spmreborn.lib.blockentity.AbstractBlockWithEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

import io.github.qwerty770.mcmod.spmreborn.blocks.entities.SweetPotatoBlockEntityTypes;
import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;

public class GrinderBlock extends AbstractBlockWithEntity<GrinderBlockEntity> {
    public static final BooleanProperty GRINDING = BooleanProperty.create("grinding");

    public GrinderBlock(BlockBehaviour.Properties settings) {
        super(settings);
        registerDefaultState(this.getStateDefinition().any().setValue(GRINDING, false));
    }

    @Override
    protected boolean blockEntityPredicate(BlockEntity blockEntity) {
        return blockEntity instanceof GrinderBlockEntity;
    }

    @Override
    public GrinderBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GrinderBlockEntity(pos, state);
    }

    @Override
    public List<ResourceLocation> incrementWhileOnUse(BlockState state, Level world, BlockPos pos, ServerPlayer serverPlayerEntity, InteractionHand hand, BlockHitResult blockHitResult) {
        return ImmutableList.of(SweetPotatoStats.INTERACT_WITH_GRINDER);
    }

    @Override
    public BlockEntityType<GrinderBlockEntity> getBlockEntityType() {
        return SweetPotatoBlockEntityTypes.GRINDER_BLOCK_ENTITY_TYPE.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GRINDING);
    }
}
