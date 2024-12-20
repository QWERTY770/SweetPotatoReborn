//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.github.qwerty770.mcmod.spmreborn.blocks.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * Because of the difficulty of extending {@link SugarCaneBlock},
 * we copied most of the codes from the class, and make
 * {@code EnchantedSugarCaneBlock} extend {@link Block}.
 *
 * @see SugarCaneBlock
 * @see #registerDefaultState(BlockState)
 */
//@SeeAlso(SugarCaneBlock.class)
public class EnchantedSugarCaneBlock extends Block {
    public static final IntegerProperty AGE;
    protected static final VoxelShape SHAPE;

    public EnchantedSugarCaneBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(world, pos)) {
            world.destroyBlock(pos, true);
        }

    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.isEmptyBlock(pos.above())) {
            int i = 1;
            while (world.getBlockState(pos.below(i)).is(this)) i++;

            if (i < 3) {
                int j = state.getValue(AGE);
                if (j == 7) {
                    world.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                    world.setBlock(pos, state.setValue(AGE, 0), 4);
                } else {
                    world.setBlock(pos, state.setValue(AGE, j + 1), 4);
                }
            }
        }

    }

    // Update to 1.21.2 -- 2024/11/15
    public @NotNull BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        if (!blockState.canSurvive(levelReader, blockPos)) {
            scheduledTickAccess.scheduleTick(blockPos, this, 1);
        }

        return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.below());
        if (blockState.getBlock() == this) {
            return true;
        } else {
            if (blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.DIRT) || blockState.is(Blocks.COARSE_DIRT) || blockState.is(Blocks.PODZOL) || blockState.is(Blocks.SAND) || blockState.is(Blocks.RED_SAND)) {
                BlockPos blockPos = pos.below();

                for (Direction direction : Plane.HORIZONTAL) {
                    BlockState blockState2 = world.getBlockState(blockPos.relative(direction));
                    FluidState fluidState = world.getFluidState(blockPos.relative(direction));
                    if (fluidState.is(FluidTags.WATER) || blockState2.is(Blocks.FROSTED_ICE)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    static {
        AGE = BlockStateProperties.AGE_7;
        SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    }
}
